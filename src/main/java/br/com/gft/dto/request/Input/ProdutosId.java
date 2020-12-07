package br.com.gft.dto.request.Input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutosId {

	@ApiModelProperty(value = "Ids dos produtos", example = "1")
	@NotNull
	public long id;
}
