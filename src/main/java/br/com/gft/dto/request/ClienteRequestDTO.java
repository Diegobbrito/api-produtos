package br.com.gft.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.gft.model.Cliente;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO {
	
	@ApiModelProperty(example = "Diego")
	@NotNull
	private String nome;
	
	@ApiModelProperty(example = "email@email.com")
	@NotEmpty
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty
	private String senha;
	
	@ApiModelProperty(value = "CPF de um cliente", example = "xxx.xxx.xx-xx")
	@NotEmpty
	@CPF(message = "CPF inválido")
	private String documento;
	
		public Cliente build() {	
			Cliente cliente = new Cliente()
					.setNome(this.nome)
					.setEmail(this.email)
					.setSenha(this.senha)
					.setDocumento(this.documento);
			return cliente;
		}

}
