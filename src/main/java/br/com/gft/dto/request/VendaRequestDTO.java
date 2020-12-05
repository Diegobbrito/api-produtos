package br.com.gft.dto.request;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import br.com.gft.dto.request.Input.ClienteId;
import br.com.gft.dto.request.Input.FornecedorId;
import br.com.gft.dto.request.Input.ProdutosId;
import br.com.gft.model.Cliente;
import br.com.gft.model.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.gft.model.Fornecedor;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaRequestDTO {

	@ApiModelProperty(value = "Id de um fornecedor", example = "1")
	@NotNull
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private FornecedorId fornecedor;

	@ApiModelProperty(value = "Id de um cliente", example = "1")
	@NotNull
	private ClienteId cliente;

	@ApiModelProperty(value = "Lista de produtos")
	@NotNull
	private List<ProdutosId> produtos;

	@ApiModelProperty( example = "30/11/2020")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCompra;

}
