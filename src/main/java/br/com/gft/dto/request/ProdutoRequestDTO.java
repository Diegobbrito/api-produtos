package br.com.gft.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import br.com.gft.model.Fornecedor;
import br.com.gft.model.Produto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO {
				
	@ApiModelProperty(value = "Nome de um produto", example = "Produdo X")
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

	@ApiModelProperty(value = "Categoria de um produto", example = "Eletronico")
	@NotNull
	private String categoria;

	@ApiModelProperty(value = "Imagem de um produto", example = "imagem.jpg")
	@NotNull
	private String imagem;

	@ApiModelProperty(value = "Quantidade de um produto", example = "5")
	@NotNull
	private long quantidade;

	@ApiModelProperty(value = "Id do fornecedor de um produto", example = "1")
	@NotNull
	private Fornecedor fornecedor;
		
		public Produto build() {	
			Produto produto = new Produto()
					.setNome(this.nome)
					.setValor(this.valor)
					.setPromocao(this.promocao)
					.setValorPromo(this.valorPromo)
					.setCategoria(this.categoria)
					.setImagem(this.imagem)
					.setQuantidade(this.quantidade)
					.setFornecedor(this.fornecedor);
			return produto;
		}

}
