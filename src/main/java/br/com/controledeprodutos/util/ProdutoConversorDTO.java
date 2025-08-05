package br.com.controledeprodutos.util;

import br.com.controledeprodutos.dto.ProdutoDTO;
import br.com.controledeprodutos.dto.ProdutoJsonDTO;
import br.com.controledeprodutos.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


public class ProdutoConversorDTO {

    public static ProdutoDTO toDTO(ProdutoEntity produtoEntity){
        return ProdutoDTO.builder()
                .nomeProduto(produtoEntity.getNomeProduto())
                .tipo(produtoEntity.getTipo())
                .nomeEmpresa(produtoEntity.getNomeEmpresa())
                .preco(produtoEntity.getPreco())
                .origem(produtoEntity.getOrigem())
                .quantidade(produtoEntity.getQuantidade())
                .id(produtoEntity.getId())
                .build();
    }

    public static ProdutoEntity toEntity(ProdutoDTO produtoDTO){
        return ProdutoEntity.builder()
                .nomeProduto(produtoDTO.getNomeProduto().toUpperCase())
                .origem(produtoDTO.getOrigem().toUpperCase())
                .tipo(produtoDTO.getTipo().toUpperCase())
                .preco(produtoDTO.getPreco())
                .nomeEmpresa(produtoDTO.getNomeEmpresa().toUpperCase())
                .quantidade(produtoDTO.getQuantidade())
                .build();
    }


    public static ProdutoEntity toEntity(ProdutoJsonDTO jsonDTO){
        return ProdutoEntity.builder()
                .nomeProduto(jsonDTO.getNomeProduto())
                .tipo(jsonDTO.getTipo())
                .preco(convertToBigDecimal(jsonDTO.getPreco()))
                .nomeEmpresa(jsonDTO.getNomeEmpresa())
                .quantidade(jsonDTO.getQuantidade())
                .origem(jsonDTO.getOrigem())
                .build();
    }

    public static List<ProdutoEntity> toEntityList(List<ProdutoJsonDTO> dtoList) {
        return dtoList.stream()
                .map(ProdutoConversorDTO::toEntity)
                .toList();
    }

    private static BigDecimal convertToBigDecimal(String valorString){

        String valorNumerico = valorString.replace("$", "");
        return new BigDecimal(valorNumerico);
    }
}
