package br.com.controledeprodutos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoJsonDTO {

    @JsonProperty("industry")
    private String nomeEmpresa;

    @JsonProperty("origin")
    private String origem;

    @JsonProperty("type")
    private String tipo;

    @JsonProperty("price")
    private String preco;

    @JsonProperty("quantity")
    private Integer quantidade;

    @JsonProperty("product")
    private String nomeProduto;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoJsonDTO that = (ProdutoJsonDTO) o;
        return Objects.equals(tipo, that.tipo) && Objects.equals(nomeProduto, that.nomeProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, nomeProduto);
    }
}
