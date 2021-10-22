package br.com.sicred.voting.gateways.http.controllers.associate.json;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateAssociateRequestJson {
	
	@NotEmpty(message = "Field name is required.")
	private String name;
	
	@NotEmpty(message = "Field cpf is required.")
	private String cpf;

}
