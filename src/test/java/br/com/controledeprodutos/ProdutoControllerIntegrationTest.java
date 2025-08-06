package br.com.controledeprodutos;

import br.com.controledeprodutos.controller.ProdutoController;
import br.com.controledeprodutos.dto.ProdutoDTO;
import br.com.controledeprodutos.exception.GlobalExceptionHandler;
import br.com.controledeprodutos.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ProdutoController.class)
@Import(GlobalExceptionHandler.class)
class ProdutoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProdutoService produtoService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void salvarProduto_deveRetornarStatus201_quandoProdutoValido() throws Exception {
        // Arrange: DTO de entrada
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNomeProduto("Celular");
        produtoDTO.setOrigem("Brasil");
        produtoDTO.setTipo("Eletrônico");
        produtoDTO.setPreco(BigDecimal.valueOf(1200.00));
        produtoDTO.setNomeEmpresa("Empresa Z");
        produtoDTO.setQuantidade(5);

        ProdutoDTO produtoSalvo = new ProdutoDTO();
        produtoSalvo.setId(1L);
        produtoSalvo.setNomeProduto("CELULAR");
        produtoSalvo.setOrigem("BRASIL");
        produtoSalvo.setTipo("ELETRÔNICO");
        produtoSalvo.setPreco(BigDecimal.valueOf(1200.00));
        produtoSalvo.setNomeEmpresa("EMPRESA Z");
        produtoSalvo.setQuantidade(5);

        when(produtoService.salvar(any(ProdutoDTO.class))).thenReturn(produtoSalvo);

        // Act & Assert
        mockMvc.perform(post("/v1/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nomeProduto").value("CELULAR"))
                .andExpect(jsonPath("$.nomeEmpresa").value("EMPRESA Z"));

        verify(produtoService).salvar(any(ProdutoDTO.class));
    }


    @Test
    void salvarProduto_deveRetornar400_quandoCamposInvalidos() throws Exception {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNomeProduto("");
        produtoDTO.setOrigem(null);
        produtoDTO.setTipo("");
        produtoDTO.setPreco(BigDecimal.ZERO);
        produtoDTO.setNomeEmpresa("");
        produtoDTO.setQuantidade(0);

        mockMvc.perform(post("/v1/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors.length()").value(Matchers.greaterThan(0)))
                .andDo(print());
    }


    @Test
    void buscarProdutos_deveRetornar200_quandoExistemProdutos() throws Exception {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(1L);
        produtoDTO.setNomeProduto("Celular");
        produtoDTO.setPreco(BigDecimal.valueOf(1200.00));
        produtoDTO.setNomeEmpresa("Empresa Z");

        Page<ProdutoDTO> page = new PageImpl<>(List.of(produtoDTO));

        when(produtoService.buscarProdutos(
                eq("celular"), isNull(), isNull(),
                eq(0), eq(10), eq("nomeProduto"), eq("asc")
        )).thenReturn(page);

        mockMvc.perform(get("/v1/produto")
                        .param("nomeProduto", "celular")
                        .param("pagina", "0")
                        .param("tamanho", "10")
                        .param("ordenarPor", "nomeProduto")
                        .param("direcao", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].nomeProduto").value("Celular"))
                .andExpect(jsonPath("$.content[0].nomeEmpresa").value("Empresa Z"));


    }

    }

