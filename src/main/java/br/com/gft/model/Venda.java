package br.com.gft.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "venda")
@Getter @Setter
@Accessors(chain = true)
public class Venda {
	
	@ApiModelProperty(value = "Codigo de um produto", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ApiModelProperty(value = "Id de um fornecedor", example = "1")
	@NotNull
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;
	
	@ApiModelProperty(value = "Id de um cliente", example = "1")
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ApiModelProperty(value = "Lista de produtos")
	@NotNull
	@ManyToMany
	@JoinTable(
	        name = "venda_produto",
	        joinColumns = @JoinColumn(name = "venda_id"),
	        inverseJoinColumns = @JoinColumn(name = "produto_id")
	    )
	private List<Produto> produtos;
	
	private BigDecimal totalCompra;
	
	@ApiModelProperty( example = "30/11/2020")
	@Column(name = "data_compra")
	private Date dataCompra;

}
