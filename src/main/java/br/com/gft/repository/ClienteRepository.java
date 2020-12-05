package br.com.gft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gft.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query(value = "SELECT * FROM cliente order by nome", nativeQuery = true)
    List<Cliente> findAllOrderByNome();
	
	@Query(value = "SELECT * FROM cliente order by nome desc", nativeQuery = true)
	List<Cliente> findAllOrderByNomeDesc();
	
	List<Cliente> findByNomeContaining(String nome);
}
 