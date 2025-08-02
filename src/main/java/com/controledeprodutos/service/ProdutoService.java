    package com.controledeprodutos.service;

    import com.controledeprodutos.dto.ProdutoDTO;
    import com.controledeprodutos.entity.ProdutoEntity;
    import com.controledeprodutos.repository.ProdutoRepository;
    import com.controledeprodutos.util.ProdutoConversor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Service;

    @Service
    @Slf4j
    public class ProdutoService {

        private final ProdutoRepository produtoRepository;

        public ProdutoService(ProdutoRepository produtoRepository) {
            this.produtoRepository = produtoRepository;
        }


        public ProdutoDTO salvar(ProdutoDTO produtoDTO){
            ProdutoEntity produtoEntity = produtoRepository.save(ProdutoConversor.toEntity(produtoDTO));
            return ProdutoConversor.toDTO(produtoEntity);
        }

       /* public ProdutoDTO consultarProduto(Long id){
            return produtoRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }*/
    }
