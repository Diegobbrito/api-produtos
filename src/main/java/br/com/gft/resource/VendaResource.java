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

import br.com.gft.model.Venda;
import br.com.gft.repository.VendaRepository;
import br.com.gft.service.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Vendas")
@RestController
@RequestMapping("/api/vendas")
public class VendaResource {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private VendaService vendaService;

	@ApiOperation("Listar todas as vendas")
	@GetMapping
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public List<Venda> listar() {
		return vendaRepository.findAll();
	}

	@ApiOperation("Buscar por ID")
	@GetMapping("/{id}")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<?> buscarPeloCodigo(
			@ApiParam(value = "Codigo de uma venda", example = "1") @PathVariable Long id) {
		Venda venda = vendaRepository.findById(id).isPresent() ? vendaRepository.findById(id).get() : null;
		return venda == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(venda);
	}

	@ApiOperation("Inserir venda")
	@PostMapping
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Venda> criar(
			@ApiParam(name = "corpo", value = "Representação de uma nova venda") @Valid @RequestBody Venda venda,
			HttpServletResponse response) {
		System.out.println(venda.getCliente().getId());
		
		Venda vendaSalva = vendaService.save(venda);
//		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);
	}

	@ApiOperation("Atualizar venda")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@PutMapping("/{id}")
	public ResponseEntity<Venda> atualizar(
			@ApiParam(value = "ID de um produto", example = "1") @PathVariable Long id,
			@ApiParam(name = "corpo", value = "Representação de uma venda com novos dados") @Valid @RequestBody Venda venda) {

		Venda vendaSalva = vendaService.atualizar(id, venda);

		return ResponseEntity.ok(vendaSalva);
	}

	@ApiOperation("Exclui produto")
	@DeleteMapping("/{id}")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@ApiParam(value = "ID de um produto", example = "1") @PathVariable Long id) {
		vendaRepository.deleteById(id);
	}

}
