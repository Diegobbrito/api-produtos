package br.com.gft.dto.response;

import br.com.gft.model.Fornecedor;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FornecedorResponseDTO {
	
	@ApiModelProperty(example = "1")
	private long id;
	@ApiModelProperty(example = "Amazon")
	private String nome;
	@ApiModelProperty(example = "75.814.019/0001-70")
	private String cnpj;
		
	public static FornecedorResponseDTO response(Fornecedor fornecedor) {
		return new FornecedorResponseDTO(
				fornecedor.getId(), 
				fornecedor.getNome(),
				fornecedor.getCnpj()
		);
	}
}
