package br.com.gft.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.gft.model.Cliente;
import br.com.gft.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;

	public Cliente save(@Valid Cliente cliente) {

		String senha = BCrypt.hashpw(cliente.getSenha(), BCrypt.gensalt());
		
		cliente.setSenha(senha);
		
		return clienteRepository.save(cliente);
	}

}
