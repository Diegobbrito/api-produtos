package br.com.gft.dto.request.Input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ClienteId {

	@ApiModelProperty(value = "Id do cliente", example = "1")
	@NotNull
	public long id;
}
