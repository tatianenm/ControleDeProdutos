    package com.controledeprodutos.api;

    import com.controledeprodutos.dto.ProdutoDTO;
    import com.controledeprodutos.service.ProdutoService;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.responses.ApiResponses;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.ErrorResponse;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/produto")
    @Tag(name = "/produto", description = "Grupo de API's para gerenciamento de estoque de produtos")
    public class ProdutoController {

        private final ProdutoService produtoService;

        public ProdutoController(ProdutoService produtoService) {
            this.produtoService = produtoService;
        }


        @Operation(description = "API para salvar um produto")
        @ResponseBody
        @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorno ok com a transação criada."),
                @ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
                @ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
                @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
        @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ProdutoDTO> salvarProduto(@RequestBody final ProdutoDTO produtoDTO) {

            if(produtoDTO == null) {
                //return ResponseEntity.badRequest().body(new ErrorResponse("Request body cannot be null"));
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(produtoDTO));
        }

      /*  @GetMapping("/{id}")
        public ResponseEntity<ProdutoDTO> consultarProduto(@PathVariable Long id) {

        }*/


    }
