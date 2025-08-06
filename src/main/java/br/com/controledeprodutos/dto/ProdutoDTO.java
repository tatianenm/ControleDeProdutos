package br.com.controledeprodutos.dto;

import jakarta.validation.constraints.Min;
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

    @NotNull(message = "Nome da empresa é obrigatório")
    private String nomeEmpresa;

    @NotNull(message = "Origem é obrigatória")
    private String origem;

    @NotBlank(message = "Tipo é obrigatório")
    private String tipo;

    @NotNull(message = "Preço é obrigatório")
    private BigDecimal preco;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade deve ser no mínimo 1")
    private Integer quantidade;

    @NotBlank(message = "Nome do produto é obrigatório")
    private String nomeProduto;

}
