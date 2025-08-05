package br.com.controledeprodutos;


import br.com.controledeprodutos.dto.ProdutoDTO;
import br.com.controledeprodutos.entity.ProdutoEntity;
import br.com.controledeprodutos.repository.ProdutoRepository;
import br.com.controledeprodutos.service.ProdutoService;
import br.com.controledeprodutos.util.ProdutoConversorDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ControleDeProdutosApplicationTests {

	@Test
	void contextLoads() {
	}

        @Mock
        private ProdutoRepository produtoRepository;

        @Autowired
        private ProdutoService produtoService;







}




