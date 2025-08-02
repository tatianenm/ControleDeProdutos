package com.controledeprodutos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoJsonDTO {

    @JsonProperty("industry")
    private String nomeEmpresa;

    @JsonProperty("origin")
    private String origem;

    @JsonProperty("type")
    private String tipo;

    @JsonProperty("price")
    private BigDecimal preco;

    @JsonProperty("quantity")
    private Integer quantidade;

    @JsonProperty("product")
    private String nomeProduto;

}
