package br.com.gft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gft.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	@Query(value = "SELECT * FROM PRODUTO order by nome", nativeQuery = true)
    List<Produto> findAllOrderByNome();
	
	@Query(value = "SELECT * FROM PRODUTO order by nome desc", nativeQuery = true)
	List<Produto> findAllOrderByNomeDesc();
	
	List<Produto> findByNomeContaining(String nome);
}
 