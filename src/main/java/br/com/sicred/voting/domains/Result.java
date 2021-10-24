package br.com.sicred.voting.domains;


import br.com.sicred.voting.domains.enums.Winner;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Result {
	
	private Integer yes;
	
	private Integer no;
	
	private Winner winner;
}
