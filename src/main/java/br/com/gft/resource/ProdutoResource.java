package br.com.gft.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gft.dto.request.ProdutoRequestDTO;
import br.com.gft.model.Produto;
import br.com.gft.repository.ProdutoRepository;
import br.com.gft.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Produtos")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoService produtoService;

	@ApiOperation("Listar todos os produtos")
	@GetMapping
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	@ApiOperation("Listar os produtos em ordem alfabética crescente por nome")
	@GetMapping("/asc")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public List<Produto> listarAsc() {
		return produtoRepository.findAllOrderByNome();
	}
	
	@ApiOperation("Listar os produtos em ordem alfabética decrescente por nome")
	@GetMapping("/desc")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public List<Produto> listarDesc() {
		return produtoRepository.findAllOrderByNomeDesc();
	}
	
	@ApiOperation("Buscar produtos por nome")
	@GetMapping("/nome/{nome}")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public List<Produto> buscarPorNome(@PathVariable String nome) {
		return produtoRepository.findByNomeContaining(nome);
	}

	@ApiOperation("Buscar por ID")
	@GetMapping("/{id}")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<Produto> buscarPeloId(
			@ApiParam(value = "ID de um produto", example = "1") 
			@PathVariable Long id) {
		Produto produto = produtoRepository.findById(id).isPresent() ? produtoRepository.findById(id).get() : null;
		return produto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(produto);
	}

	@ApiOperation("Inserir produto")
	@PostMapping
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Produto> criar(
			@ApiParam(name = "corpo", value = "Representação de um novo produto") @Valid @RequestBody ProdutoRequestDTO produto,
			HttpServletResponse response) {
		Produto produtoSalvo = produtoService.save(produto.build());
//		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}

	@ApiOperation("Atualizar produto")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(
			@ApiParam(value = "ID de um produto", example = "1") @PathVariable Long id,
			@ApiParam(name = "corpo", value = "Representação de um produto com novos dados") @Valid @RequestBody Produto produto) {

		Produto produtoSalvo = produtoService.atualizar(id, produto);

		return ResponseEntity.ok(produtoSalvo);
	}

	@ApiOperation("Exclui produto")
	@DeleteMapping("/{id}")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@ApiParam(value = "Codigo de uma pessoa", example = "1") @PathVariable Long id) {
		produtoRepository.deleteById(id);
	}

}
