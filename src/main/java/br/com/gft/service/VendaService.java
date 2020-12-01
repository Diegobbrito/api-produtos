package br.com.gft.service;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.model.Produto;
import br.com.gft.model.Venda;
import br.com.gft.repository.ProdutoRepository;
import br.com.gft.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	BigDecimal totalCompra = BigDecimal.ZERO;

	public Venda atualizar(Long id, @Valid Venda venda) {
		Venda vendaSalva = buscarVendaPeloId(id);

		BeanUtils.copyProperties(venda, vendaSalva, "id");

		return vendaRepository.save(vendaSalva);
	}

	private Venda buscarVendaPeloId(Long id) {
		Venda vendaSalva = vendaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));

		return vendaSalva;
	}
	
	
	
	public Venda save(@Valid Venda venda) {
		totalCompra = BigDecimal.ZERO;
		
		venda.getProdutos().forEach(p -> {
			Produto produto = produtoRepository.findById(p.getId()).get();
			
			if(produto.isPromocao()) {			
				totalCompra = totalCompra.add(produto.getValorPromo());
				
			}else {
				totalCompra = totalCompra.add(produto.getValor());				
			}
			
			produto.setQuantidade(produto.getQuantidade() - 1);
			produtoRepository.save(produto);
			
			venda.setTotalCompra(totalCompra);
		});

		return vendaRepository.save(venda);
	}

}
