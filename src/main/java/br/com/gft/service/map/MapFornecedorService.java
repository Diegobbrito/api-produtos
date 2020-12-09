package br.com.gft.service.map;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.dto.response.FornecedorResponseDTO;
import br.com.gft.model.Fornecedor;
import br.com.gft.repository.FornecedorRepository;

@Service
public class MapFornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	public List<FornecedorResponseDTO> listarTodos() {
		return ((List<Fornecedor>) fornecedorRepository.findAll()).stream().map(this::convertToFornecedorResponseDTO)
				.collect(Collectors.toList());
	}
	
	public List<FornecedorResponseDTO> listarAsc() {
		return ((List<Fornecedor>) fornecedorRepository.findAllOrderByNome()).stream().map(this::convertToFornecedorResponseDTO)
				.collect(Collectors.toList());
	}
	
	public List<FornecedorResponseDTO> listarDesc() {
		return ((List<Fornecedor>) fornecedorRepository.findAllOrderByNomeDesc()).stream().map(this::convertToFornecedorResponseDTO)
				.collect(Collectors.toList());
	}
	
	public List<FornecedorResponseDTO> buscarPorNome(String nome) {
		return ((List<Fornecedor>) fornecedorRepository.findByNomeContaining(nome)).stream().map(this::convertToFornecedorResponseDTO)
				.collect(Collectors.toList());
	}

	private FornecedorResponseDTO convertToFornecedorResponseDTO(Fornecedor fornecedor) {
		FornecedorResponseDTO fornecedorResponseDTO = new FornecedorResponseDTO();
		fornecedorResponseDTO.setId(fornecedor.getId());
		fornecedorResponseDTO.setNome(fornecedor.getNome());
		fornecedorResponseDTO.setCnpj(fornecedor.getCnpj());

		return fornecedorResponseDTO;
	}

}
