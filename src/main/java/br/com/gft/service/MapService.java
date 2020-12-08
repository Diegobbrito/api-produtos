package br.com.gft.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.dto.response.ClienteResponseDTO;
import br.com.gft.model.Cliente;
import br.com.gft.repository.ClienteRepository;

@Service
public class MapService {

	@Autowired
	private ClienteRepository clientRepository;

	public List<ClienteResponseDTO> listarTodos() {
		return ((List<Cliente>) clientRepository.findAll()).stream().map(this::convertToClienteResponseDTO)
				.collect(Collectors.toList());
	}
	
	public List<ClienteResponseDTO> listarAsc() {
		return ((List<Cliente>) clientRepository.findAllOrderByNome()).stream().map(this::convertToClienteResponseDTO)
				.collect(Collectors.toList());
	}
	
	public List<ClienteResponseDTO> listarDesc() {
		return ((List<Cliente>) clientRepository.findAllOrderByNomeDesc()).stream().map(this::convertToClienteResponseDTO)
				.collect(Collectors.toList());
	}
	
	public List<ClienteResponseDTO> buscarPorNome(String nome) {
		return ((List<Cliente>) clientRepository.findByNomeContaining(nome)).stream().map(this::convertToClienteResponseDTO)
				.collect(Collectors.toList());
	}

	private ClienteResponseDTO convertToClienteResponseDTO(Cliente cliente) {
		ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
		clienteResponseDTO.setId(cliente.getId());
		clienteResponseDTO.setNome(cliente.getNome());
		clienteResponseDTO.setDocumento(cliente.getDocumento());
		clienteResponseDTO.setEmail(cliente.getEmail());

		return clienteResponseDTO;
	}

}
