        package br.com.controledeprodutos.service;

        import br.com.controledeprodutos.dto.ProdutoDTO;
        import br.com.controledeprodutos.entity.ProdutoEntity;
        import br.com.controledeprodutos.repository.ProdutoRepository;
        import br.com.controledeprodutos.repository.specification.ProdutoSpecification;
        import br.com.controledeprodutos.util.ProdutoConversorDTO;
        import lombok.RequiredArgsConstructor;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.domain.Sort;
        import org.springframework.data.jpa.domain.Specification;
        import org.springframework.stereotype.Service;

        import java.math.BigDecimal;

        @Service
        @Slf4j
        @RequiredArgsConstructor
        public class ProdutoService {

            private final ProdutoRepository produtoRepository;


            public ProdutoDTO salvar(final ProdutoDTO produtoDTO){
                ProdutoEntity produtoEntity = produtoRepository.save(ProdutoConversorDTO.toEntity(produtoDTO));
                return ProdutoConversorDTO.toDTO(produtoEntity);
            }

            public Page<ProdutoDTO> buscarProdutos(final String nomeProduto, final BigDecimal precoMin,
                                                      final BigDecimal precoMax, final int pagina, final int tamanho,
                                                      final String ordenarPor, final String direcao){
                boolean temFiltro = false;

                Specification<ProdutoEntity> spec = (root, query, builder) -> builder.conjunction();

                if (nomeProduto != null && !nomeProduto.isBlank()) {
                    spec = spec.and(ProdutoSpecification.comNomeProduto(nomeProduto));
                    temFiltro = true;
                }

                if (precoMin != null || precoMax != null) {
                    BigDecimal min = precoMin != null ? precoMin : BigDecimal.ZERO;
                    BigDecimal max = precoMax != null ? precoMax : BigDecimal.valueOf(Double.MAX_VALUE);
                    spec = spec.and(ProdutoSpecification.comPrecoEntre(min, max));
                    temFiltro = true;
                }

                Sort sort = Sort.by(Sort.Direction.fromString(direcao), ordenarPor);
                Pageable pageable = PageRequest.of(pagina, tamanho, sort);
                Page<ProdutoEntity> pageResult  = temFiltro ? produtoRepository.findAll(spec, pageable) :
                                                              produtoRepository.findAll(null, pageable);

                return pageResult.map(ProdutoConversorDTO:: toDTO);

            }



        }
