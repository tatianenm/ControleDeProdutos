package br.com.controledeprodutos.dataLoad;


import br.com.controledeprodutos.dto.ProdutoDataDTO;
import br.com.controledeprodutos.dto.ProdutoJsonDTO;
import br.com.controledeprodutos.entity.ProdutoEntity;
import br.com.controledeprodutos.repository.ProdutoRepository;
import br.com.controledeprodutos.util.JsonUtil;
import br.com.controledeprodutos.util.ProdutoConversorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class CarregaMassaDados implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    public CarregaMassaDados(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Iniciando o carregamento de dados...");
        long startTime = System.currentTimeMillis();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:massaDados/*.json");

        if (resources.length == 0) {
            log.error("Nenhum arquivo JSON encontrado na pasta resources/massaDados.");
            return;
        }

        List<ProdutoJsonDTO> produtosJson = Stream.of(resources)
                .parallel()
                .flatMap(resource -> JsonUtil.readJsonFile(resource, ProdutoDataDTO.class))
                .distinct()
                .toList();


        log.debug("Total de " + produtosJson.size() + " registros únicos lidos dos arquivos.");

        List<ProdutoEntity> produtos = ProdutoConversorDTO.toEntityList(produtosJson);

        try {
            produtoRepository.saveAll(produtos);
            log.info("Todos os registros foram salvos no banco de dados.");
        } catch (Exception e) {
            log.error("Erro ao salvar registros. Possíveis duplicatas ou erro de conexão. Mensagem: " + e.getMessage());
        }

        long endTime = System.currentTimeMillis();
        log.info("Processamento concluído em " + (endTime - startTime) + " ms.");
    }

}