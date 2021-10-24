package br.com.sicred.voting.gateways.http.controllers.result.json;


import br.com.sicred.voting.domains.enums.Winner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetResultResponseJson {

	private Integer yes;
	private Integer no;
	private Winner winner;

}
