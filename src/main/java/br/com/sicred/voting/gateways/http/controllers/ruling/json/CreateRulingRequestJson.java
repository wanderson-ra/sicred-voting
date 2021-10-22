package br.com.sicred.voting.gateways.http.controllers.ruling.json;


import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor()
public class CreateRulingRequestJson {

	@NotEmpty(message = "Field name is required.")
	private String name;

	@NotEmpty(message = "Filed description is required.")
	private String description;
}
