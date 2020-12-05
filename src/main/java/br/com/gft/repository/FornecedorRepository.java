package br.com.gft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gft.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

	@Query(value = "SELECT * FROM fornecedor order by nome", nativeQuery = true)
    List<Fornecedor> findAllOrderByNome();
	
	@Query(value = "SELECT * FROM fornecedor order by nome desc", nativeQuery = true)
	List<Fornecedor> findAllOrderByNomeDesc();
	
	List<Fornecedor> findByNomeContaining(String nome);
	
}
 