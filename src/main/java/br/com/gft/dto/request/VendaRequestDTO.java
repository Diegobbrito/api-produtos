package br.com.gft.dto.request;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.gft.dto.request.Input.ClienteId;
import br.com.gft.dto.request.Input.FornecedorId;
import br.com.gft.dto.request.Input.ProdutosId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaRequestDTO {

	@ApiModelProperty(value = "Id de um fornecedor", example = "1")
	@NotNull(message = "Fornecedor não pode ser nulo")
	private FornecedorId fornecedor;

	@ApiModelProperty(value = "Id de um cliente", example = "1")
	@NotNull(message = "Cliente não pode ser nulo")
	private ClienteId cliente;

	@ApiModelProperty(value = "Lista de produtos")
	@NotEmpty(message = "Lista de produtos não pode estar vazia")
	private List<ProdutosId> produtos;

	@ApiModelProperty( example = "30/11/2020")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Data da compra não pode estar vazia")
	private Date dataCompra;

}
