package br.com.gft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gft.model.Fornecedor;
import br.com.gft.model.Produto;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

	@Query(value = "SELECT * FROM FORNECEDOR order by nome", nativeQuery = true)
    List<Produto> findAllOrderByNome();
	
	@Query(value = "SELECT * FROM FORNECEDOR order by nome desc", nativeQuery = true)
	List<Produto> findAllOrderByNomeDesc();
	
	List<Produto> findByNomeContaining(String nome);
	
}
 