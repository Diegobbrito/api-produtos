package br.com.gft.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "produto")
@Getter @Setter
@Accessors(chain = true)
public class Produto {

	@ApiModelProperty(value = "Id de um produto", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ApiModelProperty(value = "Nome de um produto", example = "Produdo X")
	private String nome;

	private String codigoProduto;

	@ApiModelProperty(value = "Valor de um produto", example = "10.50")
	private BigDecimal valor;
	
	@ApiModelProperty(value = "Produto em promoção", example = "true")
	private boolean promocao;

	@ApiModelProperty(value = "Valor de um produto em promoção", example = "9.50")
	private BigDecimal valorPromo;

	@ApiModelProperty(value = "Categoria de um produto", example = "Eletronico")
	private String categoria;

	@ApiModelProperty(value = "Imagem de um produto", example = "imagem.jpg")
	private String imagem;

	@ApiModelProperty(value = "Quantidade de um produto", example = "5")
	private long quantidade;

	@ApiModelProperty(value = "Id do fornecedor de um produto", example = "1")
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;

}
