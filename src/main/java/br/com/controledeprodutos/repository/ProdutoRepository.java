package br.com.controledeprodutos.repository;

import br.com.controledeprodutos.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<ProdutoEntity, Long>, JpaSpecificationExecutor<ProdutoEntity> {



}
