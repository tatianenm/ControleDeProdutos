package br.com.controledeprodutos;

import br.com.controledeprodutos.dto.ProdutoDTO;
import br.com.controledeprodutos.entity.ProdutoEntity;
import br.com.controledeprodutos.repository.ProdutoRepository;
import br.com.controledeprodutos.service.ProdutoService;
import br.com.controledeprodutos.util.ProdutoConversorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    private Page<ProdutoEntity> createMockPageEntity() {
        return new PageImpl<>(Collections.singletonList(new ProdutoEntity()));
    }


    @Test
    void deveSalvarProdutoDTOComSucesso() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNomeProduto("Café");
        produtoDTO.setOrigem("Brasil");
        produtoDTO.setTipo("Grão");
        produtoDTO.setPreco(BigDecimal.valueOf(15.0));
        produtoDTO.setNomeEmpresa("Empresa X");
        produtoDTO.setQuantidade(10);

        ProdutoEntity produtoEntity = ProdutoConversorDTO.toEntity(produtoDTO);
        ProdutoEntity produtoEntitySalvo = produtoEntity;
        produtoEntitySalvo.setId(1L); // simula ID gerado

        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(produtoEntitySalvo);

        ProdutoDTO resultado = produtoService.salvar(produtoDTO);

        assertNotNull(resultado);
        assertEquals(produtoDTO.getNomeProduto().toUpperCase(), resultado.getNomeProduto());
        assertEquals(1L, resultado.getId());
        verify(produtoRepository, times(1)).save(any(ProdutoEntity.class));
    }

    @Test
    void salvar_deveRetornarProdutoDTOSalvo_quandoChamadoComProdutoDTO() {
        // Arrange
        ProdutoDTO produtoDTOOriginal = new ProdutoDTO();
        produtoDTOOriginal.setNomeProduto("Celular");
        produtoDTOOriginal.setOrigem("Brasil");
        produtoDTOOriginal.setTipo("Eletrônico");
        produtoDTOOriginal.setPreco(BigDecimal.valueOf(999.99));
        produtoDTOOriginal.setNomeEmpresa("Empresa X");
        produtoDTOOriginal.setQuantidade(1);

        ProdutoEntity produtoEntityConvertido = ProdutoConversorDTO.toEntity(produtoDTOOriginal);

        ProdutoEntity produtoEntitySalvo = new ProdutoEntity();
        produtoEntitySalvo.setId(1L);
        produtoEntitySalvo.setNomeProduto(produtoEntityConvertido.getNomeProduto());
        produtoEntitySalvo.setOrigem(produtoEntityConvertido.getOrigem());
        produtoEntitySalvo.setTipo(produtoEntityConvertido.getTipo());
        produtoEntitySalvo.setPreco(produtoEntityConvertido.getPreco());
        produtoEntitySalvo.setNomeEmpresa(produtoEntityConvertido.getNomeEmpresa());
        produtoEntitySalvo.setQuantidade(produtoEntityConvertido.getQuantidade());

        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(produtoEntitySalvo);

        // Act
        ProdutoDTO resultado = produtoService.salvar(produtoDTOOriginal);

        // Assert
        assertNotNull(resultado);
        assertEquals("CELULAR", resultado.getNomeProduto());
        assertEquals("EMPRESA X", resultado.getNomeEmpresa());
        assertEquals(1L, resultado.getId());
        verify(produtoRepository, times(1)).save(any(ProdutoEntity.class));
    }

    @Test
    void buscarProdutos_deveRetornarPageDTO_quandoSemFiltros() {
        // Dados de entrada
        String nomeProduto = null;
        BigDecimal precoMin = null;
        BigDecimal precoMax = null;
        int pagina = 0;
        int tamanho = 10;
        String ordenarPor = "nome";
        String direcao = "asc";

        Sort sort = Sort.by(Sort.Direction.ASC, ordenarPor);
        Pageable pageable = PageRequest.of(pagina, tamanho, sort);


        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(1L);
        produtoEntity.setNomeProduto("PRODUTO TESTE");
        produtoEntity.setOrigem("Brasil");
        produtoEntity.setTipo("Eletrônico");
        produtoEntity.setPreco(new BigDecimal("100.00"));
        produtoEntity.setNomeEmpresa("Empresa X");
        produtoEntity.setQuantidade(5);

        List<ProdutoEntity> produtoEntityList = Collections.singletonList(produtoEntity);
        Page<ProdutoEntity> mockPageEntity = new PageImpl<>(produtoEntityList, pageable, produtoEntityList.size());

        when(produtoRepository.findAll(isNull(), eq(pageable))).thenReturn(mockPageEntity);

        Page<ProdutoDTO> resultado = produtoService.buscarProdutos(
                nomeProduto, precoMin, precoMax, pagina, tamanho, ordenarPor, direcao
        );

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        ProdutoDTO dtoConvertido = resultado.getContent().get(0);
        assertEquals("PRODUTO TESTE", dtoConvertido.getNomeProduto());

        verify(produtoRepository).findAll(isNull(), eq(pageable));
    }

    @Test
    void buscarProdutos_deveRetornarPageDTO_quandoComFiltroPorNome() {
        String nomeProduto = "celular";
        BigDecimal precoMin = null;
        BigDecimal precoMax = null;
        int pagina = 0;
        int tamanho = 10;
        String ordenarPor = "nome";
        String direcao = "asc";

        Sort sort = Sort.by(Sort.Direction.ASC, ordenarPor);
        Pageable pageable = PageRequest.of(pagina, tamanho, sort);

        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(1L);
        produtoEntity.setNomeProduto("CELULAR");
        produtoEntity.setOrigem("BRASIL");
        produtoEntity.setTipo("ELETRONICO");
        produtoEntity.setPreco(new BigDecimal("1200.00"));
        produtoEntity.setNomeEmpresa("EMPRESA Z");
        produtoEntity.setQuantidade(3);

        List<ProdutoEntity> listaEntity = List.of(produtoEntity);
        Page<ProdutoEntity> mockPageEntity = new PageImpl<>(listaEntity, pageable, listaEntity.size());

        when(produtoRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(mockPageEntity);

        Page<ProdutoDTO> resultado = produtoService.buscarProdutos(
                nomeProduto, precoMin, precoMax, pagina, tamanho, ordenarPor, direcao
        );

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        ProdutoDTO dto = resultado.getContent().get(0);
        assertEquals("CELULAR", dto.getNomeProduto());
        assertEquals("EMPRESA Z", dto.getNomeEmpresa());

        verify(produtoRepository).findAll(any(Specification.class), eq(pageable));

    }

}