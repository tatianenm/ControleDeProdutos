package com.controledeprodutos.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProdutoDTO {

    private String nomeEmpresa;

    private String origem;

    private String tipo;

    private BigDecimal preco;

    private Integer quantidade;

    private String nomeProduto;

}
