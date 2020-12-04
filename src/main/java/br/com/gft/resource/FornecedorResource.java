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

import br.com.gft.dto.request.FornecedorRequestDTO;
import br.com.gft.dto.response.FornecedorResponseDTO;
import br.com.gft.model.Fornecedor;
import br.com.gft.repository.FornecedorRepository;
import br.com.gft.service.FornecedorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Fornecedores")
@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorResource {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private FornecedorService fornecedorService;

	@ApiOperation("Listar todos os fornecedores")
	@GetMapping
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public List<Fornecedor> listar() {
		return fornecedorRepository.findAll();
	}

	@ApiOperation("Listar os fornecedores em ordem alfabética crescente por nome")
	@GetMapping("/asc")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public List<Fornecedor> listarAsc() {
		return fornecedorRepository.findAllOrderByNome();
	}

	@ApiOperation("Listar os fornecedores em ordem alfabética decrescente por nome")
	@GetMapping("/desc")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public List<Fornecedor> listarDesc() {
		return fornecedorRepository.findAllOrderByNomeDesc();
	}

	@ApiOperation("Buscar fornecedores por nome")
	@GetMapping("/nome/{nome}")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public List<Fornecedor> buscarPorNome(@PathVariable String nome) {
		return fornecedorRepository.findByNomeContaining(nome);
	}

	@ApiOperation("Buscar por ID")
	@GetMapping("/{id}")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<Fornecedor> buscarPeloCodigo(
			@ApiParam(value = "ID de um fornecedor", example = "1") @PathVariable Long id) {
		Fornecedor fornecedor = fornecedorRepository.findById(id).isPresent() ? fornecedorRepository.findById(id).get()
				: null;
		return fornecedor != null ? ResponseEntity.ok(fornecedor) : ResponseEntity.notFound().build();
	}

	@ApiOperation("Inserir fornecedor")
	@PostMapping
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<FornecedorResponseDTO> criar(
			@ApiParam(name = "corpo", value = "Representação de um novo cliente")@Valid @RequestBody FornecedorRequestDTO fornecedor,
			HttpServletResponse response) {
		Fornecedor fornecedorSalvo = fornecedorService.save(fornecedor.build());

//		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getCodigo()));
		return new ResponseEntity<>(FornecedorResponseDTO.response(fornecedorSalvo), HttpStatus.CREATED);

	}
	
	@ApiOperation("Atualizar fornecedor")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> atualizar(
			@ApiParam(example = "1") @PathVariable Long id,
			@ApiParam(name = "corpo", value = "Representação de um fornecedor com novos dados") @Valid @RequestBody Fornecedor fornecedor) {

		Fornecedor fornecedorSalvo = fornecedorService.atualizar(id, fornecedor);

		return ResponseEntity.ok(fornecedorSalvo);
	}

	@ApiOperation("Excluir fornecedor")
	@DeleteMapping("/{id}")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@ApiParam(example = "1") @PathVariable Long id) {
		fornecedorRepository.deleteById(id);
	}

}
