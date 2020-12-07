package br.com.gft.dto.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.gft.model.Fornecedor;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FornecedorRequestDTO {
				
		@ApiModelProperty(example = "Amazon")
		@NotNull(message = "O nome não pode ser nulo")
		private String nome;
		
		@ApiModelProperty(example = "xx.xxx.xxx/xxxx-xx")
		@NotNull(message = "O CNPJ não pode ser nulo")
		@CNPJ
		private String cnpj;
		
		public Fornecedor build() {	
			Fornecedor fornecedor = new Fornecedor()
					.setCnpj(this.cnpj)
					.setNome(this.nome);
			return fornecedor;
		}

}
