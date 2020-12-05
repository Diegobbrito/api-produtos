package br.com.gft.dto.response;

import br.com.gft.model.Venda;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VendaResponseDTO {
	
	@ApiModelProperty(example = "1")
	private long id;

	@ApiModelProperty(example = "Amazon")
	private String fornecedor;

	@ApiModelProperty(example = "Diego")
	private String cliente;
	@ApiModelProperty(example = "150.00")
	private BigDecimal totalCompra;

	public static VendaResponseDTO response(Venda venda) {
		return new VendaResponseDTO(
				venda.getId(),
				venda.getFornecedor().getNome(),
				venda.getCliente().getNome(),
				venda.getTotalCompra()
		);
	}
}
