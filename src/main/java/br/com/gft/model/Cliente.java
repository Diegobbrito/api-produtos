package br.com.gft.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "cliente")
@Getter @Setter
@Accessors(chain = true)
public class Cliente {
	
	@ApiModelProperty(value = "ID de um cliente", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ApiModelProperty(value = "Nome de um cliente", example = "Diego")
	private String nome;
	
	@ApiModelProperty(value = "Email de um cliente", example = "email@email.com")
	private String email;
	
	private String senha;
	
	@ApiModelProperty(value = "CPF de um cliente", example = "xxx.xxx.xx-xx")
	private String documento;
	
	private LocalDate dataCadastro;

}
