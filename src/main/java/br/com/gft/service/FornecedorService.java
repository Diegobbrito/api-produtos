package br.com.gft.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.model.Fornecedor;
import br.com.gft.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	FornecedorRepository fornecedorRepository;

	public Fornecedor save(@Valid Fornecedor fornecedor) {
		
		return fornecedorRepository.save(fornecedor);
	}

	public Fornecedor atualizar(Long id, @Valid Fornecedor fornecedor) {
		Fornecedor fornecedorSalvo = buscarFornecedorPeloId(id);

		BeanUtils.copyProperties(fornecedor, fornecedorSalvo, "id");

		return fornecedorRepository.save(fornecedorSalvo);
	}
	
	public void excluir(Long id) {
		Fornecedor fornecedor = buscarFornecedorPeloId(id);
		
		fornecedorRepository.delete(fornecedor);
	}
	

	private Fornecedor buscarFornecedorPeloId(Long id) {
		Fornecedor fornecedor = fornecedorRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));

		return fornecedor;
	}

	

}
