package br.com.controledeprodutos.repository.specification;

import br.com.controledeprodutos.entity.ProdutoEntity;
import org.springframework.data.jpa.domain.Specification;
import java.math.BigDecimal;

public class ProdutoSpecification {

    public static Specification<ProdutoEntity> comNomeProduto(String nomeProduto) {
        return (root, query, builder) ->
                nomeProduto == null ? null : builder.like(builder.lower(root.get("nomeProduto")), "%" + nomeProduto.toLowerCase() + "%");
    }

    public static Specification<ProdutoEntity> comNomeEmpresa(String nomeEmpresa) {
        return (root, query, builder) ->
                nomeEmpresa == null ? null : builder.like(builder.lower(root.get("nomeEmpresa")), "%" + nomeEmpresa.toLowerCase() + "%");
    }

    public static Specification<ProdutoEntity> comOrigem(String origem) {
        return (root, query, builder) ->
                origem == null ? null : builder.equal(root.get("origem"), origem);
    }

    public static Specification<ProdutoEntity> comTipo(String tipo) {
        return (root, query, builder) ->
                tipo == null ? null : builder.equal(root.get("tipo"), tipo);
    }

    public static Specification<ProdutoEntity> comPrecoMenorQue(BigDecimal preco) {
        return (root, query, builder) ->
                preco == null ? null : builder.lessThanOrEqualTo(root.get("preco"), preco);
    }

    public static Specification<ProdutoEntity> comQuantidadeMaiorQue(Integer quantidade) {
        return (root, query, builder) ->
                quantidade == null ? null : builder.greaterThanOrEqualTo(root.get("quantidade"), quantidade);
    }

    public static Specification<ProdutoEntity> comPrecoEntre(BigDecimal min, BigDecimal max) {
        return (root, query, builder) ->
                builder.between(root.get("preco"), min, max);
    }


}
