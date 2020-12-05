package br.com.gft.dto.request.Input;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class FornecedorId {

	@ApiModelProperty(value = "Id do fornecedor de um produto", example = "1")
	@NotNull
	public long id;
}
