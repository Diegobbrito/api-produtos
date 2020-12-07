package br.com.gft.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.gft.dto.request.Input.FornecedorId;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO {
				
	@ApiModelProperty( example = "Produto X")
	@NotNull(message = "O nome não pode ser nulo")
	private String nome;

	@ApiModelProperty(value = "Valor de um produto", example = "10.50")
	@NotNull(message = "O valor não pode ser nulo")
	@Min(value = 1, message = "O valor mínimo é 1")
	private BigDecimal valor;
	
	@ApiModelProperty(value = "Produto em promoção", example = "true")
	@NotNull(message = "O status da promoção não pode ser nulo")
	private boolean promocao;

	@ApiModelProperty(value = "Valor de um produto em promoção", example = "9.50")
	private BigDecimal valorPromo;

	@ApiModelProperty(value = "Categoria de um produto", example = "Eletrônico")
	@NotNull(message = "A categoria não pode ser nula")
	private String categoria;

	@ApiModelProperty(example = "imagem.jpg")
	@NotNull(message = "A imagem não pode ser nula")
	private String imagem;

	@ApiModelProperty(value = "Quantidade de um produto", example = "5")
	@NotNull(message = "A quantidade não pode ser nula")
	private long quantidade;

	@NotNull(message = "O fornecedor não pode ser nulo")
	private FornecedorId fornecedor;
}
