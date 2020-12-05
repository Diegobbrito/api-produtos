package br.com.gft.dto.request;

import java.math.BigDecimal;

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
	@NotNull
	private String nome;

	@ApiModelProperty(value = "Valor de um produto", example = "10.50")
	@NotNull
	private BigDecimal valor;
	
	@ApiModelProperty(value = "Produto em promoção", example = "true")
	@NotNull
	private boolean promocao;

	@ApiModelProperty(value = "Valor de um produto em promoção", example = "9.50")
	@NotNull
	private BigDecimal valorPromo;

	@ApiModelProperty(value = "Categoria de um produto", example = "Eletrônico")
	@NotNull
	private String categoria;

	@ApiModelProperty(example = "imagem.jpg")
	@NotNull
	private String imagem;

	@ApiModelProperty(value = "Quantidade de um produto", example = "5")
	@NotNull
	private long quantidade;

	private FornecedorId fornecedor;
}
