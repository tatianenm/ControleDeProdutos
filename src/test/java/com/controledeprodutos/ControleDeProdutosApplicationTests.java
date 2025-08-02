package com.controledeprodutos;

import com.controledeprodutos.dto.ProdutoDTO;
import com.controledeprodutos.entity.ProdutoEntity;
import com.controledeprodutos.repository.ProdutoRepository;
import com.controledeprodutos.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/*@SpringBootTest
class ControleDeProdutosApplicationTests {

	@Test
	void contextLoads() {
	}

        @Mock
        private ProdutoRepository produtoRepository;

        @Autowired
        private ProdutoService produtoService;

        @Test
        void deveSalvarProdutoComSucesso() {
            ProdutoDTO dto = new ProdutoDTO();
            dto.setNomeProduto("Mouse Gamer");
            dto.setPreco(BigDecimal.valueOf(12.99));

            ProdutoEntity produtoMock = ProdutoEntity.builder().id(1L).nomeProduto(dto.getNomeProduto()).preco(dto.getPreco()).build();
            Result result = new Result(dto, produtoMock);

            when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(result.produtoMock());

            ProdutoEntity salvo = produtoService.salvar(result.dto());

            assertNotNull(salvo.getId());
            assertEquals("Mouse Gamer", salvo.getNomeProduto());
        }

    private record Result(ProdutoDTO dto, ProdutoEntity produtoMock) {


    }
}*/




