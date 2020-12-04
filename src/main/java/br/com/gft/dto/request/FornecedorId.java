package br.com.gft.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class FornecedorId {
	@NotNull
	public long id;
}
