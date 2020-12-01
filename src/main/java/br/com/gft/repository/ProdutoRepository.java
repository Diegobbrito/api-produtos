package br.com.gft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
 