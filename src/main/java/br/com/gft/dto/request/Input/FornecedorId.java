package br.com.gft.dto.request.Input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FornecedorId {

	@ApiModelProperty(example = "1")
	public long id;
}
