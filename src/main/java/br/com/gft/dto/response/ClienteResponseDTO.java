package br.com.gft.dto.response;

import br.com.gft.model.Cliente;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {

	@ApiModelProperty(example = "1")
	private long id;

	@ApiModelProperty(example = "Diego")
	private String nome;

	@ApiModelProperty(example = "email@email.com")
	private String email;

	@ApiModelProperty(example = "xxx.xxx.xx-xx")
	private String documento;

	public static ClienteResponseDTO response(Cliente cliente) {
		return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getDocumento());
	}

}
