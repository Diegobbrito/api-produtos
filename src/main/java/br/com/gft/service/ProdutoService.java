package br.com.gft.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.model.Produto;
import br.com.gft.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto atualizar(Long id, @Valid Produto produto) {
		Produto produtoSalvo = buscarProdutoPeloId(id);

		BeanUtils.copyProperties(produto, produtoSalvo, "id");

		return produtoRepository.save(produtoSalvo);
	}

	private Produto buscarProdutoPeloId(Long id) {
		Produto produtoSalvo = produtoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));

		return produtoSalvo;
	}


}
