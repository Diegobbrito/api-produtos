package br.com.gft.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gft.model.Cliente;
import br.com.gft.model.Produto;
import br.com.gft.repository.ClienteRepository;
import br.com.gft.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;

//	@Autowired
//	private ApplicationEventPublisher publisher;

	@ApiOperation("Lista todos os clientes")
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@ApiOperation("Listar os clientes em ordem alfabética crescente por nome")
	@GetMapping("/asc")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public List<Produto> listarAsc() {
		return clienteRepository.findAllOrderByNome();
	}
	
	@ApiOperation("Listar os clientes em ordem alfabética decrescente por nome")
	@GetMapping("/desc")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public List<Produto> listarDesc() {
		return clienteRepository.findAllOrderByNomeDesc();
	}
	
	@ApiOperation("Buscar clientes por nome")
	@GetMapping("/nome/{nome}")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public List<Produto> buscarPorNome(@PathVariable String nome) {
		return clienteRepository.findByNomeContaining(nome);
	}

	@ApiOperation("Cria um novo cliente")
	@PostMapping				
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cliente> criar(		
			@ApiParam(name = "corpo", value = "Representação de um novo cliente") @Valid @RequestBody Cliente cliente,
			HttpServletResponse response) {
		Cliente clienteSalvo = clienteService.save(cliente);

//		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);

	}

	@ApiOperation("Busca um cliente pelo ID")
	@GetMapping("/{id}")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<Cliente> buscarPeloId(
			@ApiParam(value = "Id de um cliente", example = "1") @PathVariable Long id) {
		Cliente cliente = clienteRepository.findById(id).isPresent() ? clienteRepository.findById(id).get() : null;
		return cliente == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(cliente);
	}

}
