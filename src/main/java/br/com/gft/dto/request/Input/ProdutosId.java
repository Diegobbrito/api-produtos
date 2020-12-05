package br.com.gft.dto.request.Input;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutosId {

	@ApiModelProperty(value = "Ids dos produtos", example = "1")
	@NotNull
	public long id;
}
