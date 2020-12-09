package br.com.gft.service.map;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.dto.response.VendaResponseDTO;
import br.com.gft.model.Venda;
import br.com.gft.repository.VendaRepository;

@Service
public class MapVendaService {

	@Autowired
	private VendaRepository vendaRepository;

	public List<VendaResponseDTO> listarTodos() {
		return ((List<Venda>) vendaRepository.findAll()).stream().map(this::convertToFornecedorResponseDTO)
				.collect(Collectors.toList());
	}
	
	private VendaResponseDTO convertToFornecedorResponseDTO(Venda venda) {
		VendaResponseDTO vendaResponseDTO = new VendaResponseDTO();
		vendaResponseDTO.setId(venda.getId());
		vendaResponseDTO.setFornecedor(venda.getFornecedor().getNome());
		vendaResponseDTO.setCliente(venda.getCliente().getNome());
		vendaResponseDTO.setTotalCompra(venda.getTotalCompra());
		
		return vendaResponseDTO;
	}

}
