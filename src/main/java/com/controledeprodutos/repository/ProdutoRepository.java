package com.controledeprodutos.repository;

import com.controledeprodutos.entity.ProdutoEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<ProdutoEntity, Long> {

}
