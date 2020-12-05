package br.com.gft.service;

import java.util.Random;

import javax.validation.Valid;

import br.com.gft.repository.FornecedorRepository;
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

	@Autowired
	private FornecedorRepository fornecedorRepository;


	public Produto atualizar(Long id, @Valid Produto produto) {
		Produto produtoSalvo = buscarProdutoPeloId(id);

		BeanUtils.copyProperties(produto, produtoSalvo, "id");

		return produtoRepository.save(produtoSalvo);
	}

	private Produto buscarProdutoPeloId(Long id) {
		Produto produto = produtoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));

		return produto;
	}

	public Produto save(@Valid Produto produto) {
		
		if(produto.getValorPromo() == null && !produto.isPromocao()) produto.setValorPromo(produto.getValor());

		Random gerador = new Random();
		
		produto.setCodigoProduto("#" + gerador.hashCode());

		return produtoRepository.save(produto);
	}
}
