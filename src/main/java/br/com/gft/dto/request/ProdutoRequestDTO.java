package br.com.gft.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.gft.model.Fornecedor;
import br.com.gft.model.Produto;
import br.com.gft.repository.FornecedorRepository;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO {
	
	@Autowired
	FornecedorRepository fornecedorRepository;
				
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
	private FornecedorId fornecedor;
		
		public Produto build() {	
			System.out.println(fornecedor.getId());
			Fornecedor f = null;
			if(fornecedorRepository.findById(fornecedor.getId()).isPresent())
					f = fornecedorRepository.findById(fornecedor.getId()).get();

			Produto produto = new Produto()
					.setNome(this.nome)
					.setValor(this.valor)
					.setPromocao(this.promocao)
					.setValorPromo(this.valorPromo)
					.setCategoria(this.categoria)
					.setImagem(this.imagem)
					.setQuantidade(this.quantidade)
					.setFornecedor(f);
			return produto;
		}

}
