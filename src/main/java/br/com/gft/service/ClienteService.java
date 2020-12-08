package br.com.gft.service;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.gft.model.Cliente;
import br.com.gft.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;

	public Cliente save(@Valid Cliente cliente) {

		gerarSenha(cliente);
		
		cliente.setDataCadastro(LocalDate.now());
		
		return clienteRepository.save(cliente);
	}

	public Cliente atualizar(Long id, @Valid Cliente cliente) {
		Cliente clienteSalvo = buscarClientePeloId(id);

		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		
		clienteSalvo.setDataCadastro(LocalDate.now());
		gerarSenha(clienteSalvo);
		
		return clienteRepository.save(clienteSalvo);
	}
	
	public void excluir(Long id) {
		Cliente cliente = buscarClientePeloId(id);
		clienteRepository.delete(cliente);	
	}
	
	private void gerarSenha(Cliente cliente) {
		String senha = BCrypt.hashpw(cliente.getSenha(), BCrypt.gensalt());
		cliente.setSenha(senha);
	}
	
	private Cliente buscarClientePeloId(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));

		return cliente;
	}

}
