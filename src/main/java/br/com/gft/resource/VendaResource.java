package br.com.gft.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import br.com.gft.dto.request.VendaRequestDTO;
import br.com.gft.dto.response.VendaResponseDTO;
import br.com.gft.model.Cliente;
import br.com.gft.model.Fornecedor;
import br.com.gft.model.Produto;
import br.com.gft.model.Venda;
import br.com.gft.repository.ClienteRepository;
import br.com.gft.repository.FornecedorRepository;
import br.com.gft.repository.ProdutoRepository;
import br.com.gft.repository.VendaRepository;
import br.com.gft.service.VendaService;
import br.com.gft.service.map.MapVendaService;
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
    private ClienteRepository clienteRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private MapVendaService mapService;

    @Autowired
    private VendaService vendaService;

    @ApiOperation("Listar todas as vendas")
    @GetMapping
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public List<VendaResponseDTO> listar() {
        return mapService.listarTodos();
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
    public ResponseEntity<VendaResponseDTO> criar(
            @ApiParam(name = "corpo", value = "Representação de uma nova venda") @Valid @RequestBody VendaRequestDTO vendaRequestDTO,
            HttpServletResponse response) {
        Venda venda = toDomainObject(vendaRequestDTO);

        Venda vendaSalva = vendaService.save(venda);
//		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
        return new ResponseEntity<>(VendaResponseDTO.response(vendaSalva), HttpStatus.CREATED);
    }

    @ApiOperation("Atualizar venda")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizar(
            @ApiParam(value = "ID de um produto", example = "1") @PathVariable Long id,
            @ApiParam(name = "corpo", value = "Representação de uma venda com novos dados") @Valid @RequestBody VendaRequestDTO vendaRequestDTO) {

    	Venda venda = toDomainObject(vendaRequestDTO);
    	
    	Venda vendaSalva = vendaService.atualizar(id, venda);

        return ResponseEntity.ok(vendaSalva);
    }

    @ApiOperation("Exclui venda")
    @DeleteMapping("/{id}")
//	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@ApiParam(value = "ID de uma venda", example = "1") @PathVariable Long id) {
    	vendaService.excluir(id);
    }

    private Venda toDomainObject(VendaRequestDTO vendaRequestDTO) {

        Venda venda = new Venda()
                .setDataCompra(vendaRequestDTO.getDataCompra());

        if (fornecedorRepository.findById(vendaRequestDTO.getFornecedor().getId()).isPresent()) {
            Fornecedor fornecedor = fornecedorRepository.findById(vendaRequestDTO.getFornecedor().getId()).get();
            venda.setFornecedor(fornecedor);
        }

        if (clienteRepository.findById(vendaRequestDTO.getCliente().getId()).isPresent()) {
            Cliente cliente = clienteRepository.findById(vendaRequestDTO.getCliente().getId()).get();
            venda.setCliente(cliente);
        }

        if(vendaRequestDTO.getProdutos().isEmpty()) {
			throw new DataIntegrityViolationException("Produtos inválidos");
		}else {
			List<Produto> listProdutos = new ArrayList<Produto>();
			
			long fornecedorId = vendaRequestDTO.getFornecedor().getId();
			
			vendaRequestDTO.getProdutos().forEach(produtosId -> {
				
				if(produtoRepository.findById(produtosId.getId()).isEmpty()) {
					throw new NoSuchElementException("Produto não encontrado para o id " + produtosId.getId());
				}
					Produto produto = produtoRepository.findById(produtosId.getId()).get();
							
				if (produto.getFornecedor().getId() == fornecedorId) {
					listProdutos.add(produto);                        	
				}
				else {
					throw new DataIntegrityViolationException("Produtos não são do mesmo fornecedor");
				}
			});

			venda.setProdutos(listProdutos);
		}
        
        return venda;
    }

}
