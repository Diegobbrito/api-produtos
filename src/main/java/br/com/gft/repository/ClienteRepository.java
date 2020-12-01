package br.com.gft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
 