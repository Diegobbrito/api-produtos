package br.com.gft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gft.model.Cliente;
import br.com.gft.model.Produto;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query(value = "SELECT * FROM CLIENTE order by nome", nativeQuery = true)
    List<Produto> findAllOrderByNome();
	
	@Query(value = "SELECT * FROM PRODUTO CLIENTE by nome desc", nativeQuery = true)
	List<Produto> findAllOrderByNomeDesc();
	
	List<Produto> findByNomeContaining(String nome);
}
 