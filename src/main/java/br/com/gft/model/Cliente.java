package br.com.gft.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter @Setter
public class Cliente {
	
	@ApiModelProperty(value = "ID de um cliente", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ApiModelProperty(value = "Nome de um cliente", example = "Diego")
	@NotNull
	private String nome;
	
	@ApiModelProperty(value = "Email de um cliente", example = "email@email.com")
	@NotEmpty
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty
	private String senha;
	
	@ApiModelProperty(value = "CPF de um cliente", example = "xxx.xxx.xx-xx")
	@NotEmpty
	@CPF
	@Column(unique = true)
	private String documento;
	
	private LocalDate dataCadastro;

}
