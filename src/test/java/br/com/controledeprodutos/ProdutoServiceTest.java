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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoConversorDTO produtoConversorDTO;

    @InjectMocks
    private ProdutoService produtoService;

    private Page<ProdutoEntity> createMockPageEntity() {
        return new PageImpl<>(Collections.singletonList(new ProdutoEntity()));
    }

    private Page<ProdutoDTO> createMockPageDTO() {
        return new PageImpl<>(Collections.singletonList(new ProdutoDTO()));
    }

    @Test
    void salvar_deveRetornarProdutoDTOSalvo_quandoChamadoComProdutoDTO() {
        // 1. Arrange: Preparar os objetos de teste e o comportamento dos mocks.
        ProdutoDTO produtoDTOOriginal = new ProdutoDTO();
        produtoDTOOriginal.setNomeProduto("Celular");

        ProdutoEntity produtoEntityConvertido = new ProdutoEntity();
        produtoEntityConvertido.setNomeProduto("Celular");

        ProdutoEntity produtoEntitySalvo = new ProdutoEntity();
        produtoEntitySalvo.setId(1L);
        produtoEntitySalvo.setNomeProduto("Celular");

        ProdutoDTO produtoDTORetornado = new ProdutoDTO();
        produtoDTORetornado.setId(1L);
        produtoDTORetornado.setNomeProduto("Celular");


        when(produtoConversorDTO.toEntity(produtoDTOOriginal)).thenReturn(produtoEntityConvertido);

        when(produtoRepository.save(produtoEntityConvertido)).thenReturn(produtoEntitySalvo);

        when(produtoConversorDTO.toDTO(produtoEntitySalvo)).thenReturn(produtoDTORetornado);

        ProdutoDTO resultado = produtoService.salvar(produtoDTOOriginal);

        assertEquals(produtoDTORetornado, resultado, "O DTO retornado deve ser o mesmo que o DTO mockado.");

        verify(produtoConversorDTO, times(1)).toEntity(produtoDTOOriginal);
        verify(produtoRepository, times(1)).save(produtoEntityConvertido);
        verify(produtoConversorDTO, times(1)).toDTO(produtoEntitySalvo);
    }

    @Test
    void buscarProdutos_deveRetornarPageDTO_quandoSemFiltros() {
        String nomeProduto = null;
        BigDecimal precoMin = null;
        BigDecimal precoMax = null;
        int pagina = 0;
        int tamanho = 10;
        String ordenarPor = "nome";
        String direcao = "asc";

        Sort sort = Sort.by(Sort.Direction.ASC, ordenarPor);
        Pageable pageable = PageRequest.of(pagina, tamanho, sort);

        Page<ProdutoEntity> mockPageEntity = createMockPageEntity();
        Page<ProdutoDTO> mockPageDTO = createMockPageDTO();

        when(produtoRepository.findAll(eq(null), eq(pageable))).thenReturn(mockPageEntity);

        when(produtoConversorDTO.toDTO(any(ProdutoEntity.class))).thenReturn(new ProdutoDTO());

        Page<ProdutoDTO> resultado = produtoService.buscarProdutos(nomeProduto, precoMin, precoMax, pagina, tamanho, ordenarPor, direcao);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());

        verify(produtoRepository).findAll(eq(null), eq(pageable));
    }



    @Test
    void buscarProdutos_deveRetornarPageDTO_quandoComFiltroPorNome() {
        // Arrange
        String nomeProduto = "celular";
        BigDecimal precoMin = null;
        BigDecimal precoMax = null;
        int pagina = 0;
        int tamanho = 10;
        String ordenarPor = "nome";
        String direcao = "asc";

        Sort sort = Sort.by(Sort.Direction.ASC, ordenarPor);
        Pageable pageable = PageRequest.of(pagina, tamanho, sort);

        Page<ProdutoEntity> mockPageEntity = createMockPageEntity();

        when(produtoRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(mockPageEntity);

        when(produtoConversorDTO.toDTO(any(ProdutoEntity.class))).thenReturn(new ProdutoDTO());


        Page<ProdutoDTO> resultado = produtoService.buscarProdutos(nomeProduto, precoMin, precoMax, pagina, tamanho, ordenarPor, direcao);


        assertNotNull(resultado);
        verify(produtoRepository).findAll(any(Specification.class), eq(pageable));
    }



}