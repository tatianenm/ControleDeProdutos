    package br.com.controledeprodutos.controller;

    import br.com.controledeprodutos.dto.ProdutoDTO;
    import br.com.controledeprodutos.service.ProdutoService;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.responses.ApiResponses;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.domain.Page;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.math.BigDecimal;
    import java.util.NoSuchElementException;

    @RestController
    @RequestMapping("/v1/produto")
    @Tag(name = "/v1/produto", description = "Grupo de API's para gerenciamento de estoque de produtos")
    @RequiredArgsConstructor
    public class ProdutoController {

        private final ProdutoService produtoService;

        @Operation(description = "API para salvar  produto")
        @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorno ok com o produto salvo."),
                               @ApiResponse(responseCode = "400", description = "Todos os campos devem ser preenchidos."),
                               @ApiResponse(responseCode = "409", description = "Erro de integridade de dados.")})
        @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ProdutoDTO> salvarProduto(@Valid @RequestBody final ProdutoDTO produtoDTO) {

            return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(produtoDTO));
        }


        @Operation(description = "API para buscar produtos")
        @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno ok da lista de produtos"),
                               @ApiResponse(responseCode = "404", description = "Registro não encontrado")})
        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Page<ProdutoDTO>> buscarProdutos(
                @RequestParam(required = false) String nomeProduto,
                @RequestParam(required = false) BigDecimal precoMin,
                @RequestParam(required = false) BigDecimal precoMax,
                @RequestParam(defaultValue = "0") int pagina,
                @RequestParam(defaultValue = "10") int tamanho,
                @RequestParam(defaultValue = "nomeProduto") String ordenarPor,
                @RequestParam(defaultValue = "asc") String direcao
        ) {

           var produtos =  produtoService.buscarProdutos(nomeProduto, precoMin, precoMax, pagina, tamanho,
                    ordenarPor, direcao);
            if (produtos.isEmpty()) {
                throw new NoSuchElementException();
            }

            return ResponseEntity.ok(produtos);
        }



    }
