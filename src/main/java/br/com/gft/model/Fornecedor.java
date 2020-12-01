package br.com.gft.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fornecedor")
@Getter @Setter
public class Fornecedor {
	
	@ApiModelProperty(value = "Codigo de um produto", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ApiModelProperty(value = "Nome de um fornecedor", example = "Amazon")
	@NotNull
	private String nome;
	
	@ApiModelProperty(value = "Cnpj de um fornecedor", example = "xx.xxx.xxx/xxxx-xx")
	@NotNull
	private String cnpj;

	@JsonManagedReference
	@OneToMany(mappedBy = "fornecedor")
	private List<Produto> produtos;
}
