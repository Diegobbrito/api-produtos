package br.com.gft.dto.response;

import br.com.gft.dto.request.Input.ProdutosId;
import br.com.gft.model.Produto;
import br.com.gft.model.Venda;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class VendaResponseDTO {
	
	@ApiModelProperty(example = "1")
	private long id;

	@ApiModelProperty(example = "Amazon")
	private String nomeFornecedor;

	@ApiModelProperty(example = "Diego")
	private String nomeCliente;

	private List<Produto> produtos;

	private BigDecimal totalCompra;


		
	public static VendaResponseDTO response(Venda venda) {
		List<Long> ids = new ArrayList<>();
		venda.getProdutos().forEach(produto -> {
			ids.add(produto.getId());
		});
		return new VendaResponseDTO(
				venda.getId(),
				venda.getFornecedor().getNome(),
				venda.getCliente().getNome(),
				venda.getProdutos(),
				venda.getTotalCompra()
		);
	}
}
