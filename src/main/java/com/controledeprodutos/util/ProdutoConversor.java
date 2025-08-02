package com.controledeprodutos.util;

import com.controledeprodutos.dto.ProdutoDTO;
import com.controledeprodutos.entity.ProdutoEntity;

public class ProdutoConversor {

    public static ProdutoDTO toDTO(ProdutoEntity produtoEntity){
        return ProdutoDTO.builder()
                .nomeProduto(produtoEntity.getNomeProduto())
                .tipo(produtoEntity.getTipo())
                .nomeEmpresa(produtoEntity.getNomeEmpresa())
                .preco(produtoEntity.getPreco())
                .origem(produtoEntity.getOrigem())
                .quantidade(produtoEntity.getQuantidade())
                .build();
    }

    public static ProdutoEntity toEntity(ProdutoDTO produtoDTO){
        return ProdutoEntity.builder()
                .nomeProduto(produtoDTO.getNomeProduto())
                .origem(produtoDTO.getOrigem())
                .tipo(produtoDTO.getTipo())
                .preco(produtoDTO.getPreco())
                .nomeEmpresa(produtoDTO.getNomeEmpresa())
                .quantidade(produtoDTO.getQuantidade())
                .build();
    }
}
