package br.com.gft.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.model.Venda;
import br.com.gft.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	public Venda atualizar(Long id, @Valid Venda venda) {
		Venda vendaSalva = buscarVendaPeloId(id);

		BeanUtils.copyProperties(venda, vendaSalva, "id");

		return vendaRepository.save(vendaSalva);
	}

	private Venda buscarVendaPeloId(Long id) {
		Venda vendaSalva = vendaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));

		return vendaSalva;
	}
	
}
