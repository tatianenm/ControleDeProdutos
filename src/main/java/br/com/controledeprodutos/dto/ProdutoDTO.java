package br.com.controledeprodutos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long id;

    @NotNull
    private String nomeEmpresa;

    @NotNull
    private String origem;

    @NotNull
    @NotBlank
    private String tipo;

    @NotNull
    private BigDecimal preco;

    @NotNull
    private Integer quantidade;

    @NotNull
    @NotBlank
    private String nomeProduto;

}
