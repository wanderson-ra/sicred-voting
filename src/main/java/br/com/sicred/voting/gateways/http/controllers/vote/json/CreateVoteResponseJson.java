package br.com.sicred.voting.gateways.http.controllers.vote.json;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class CreateVoteResponseJson {

	private String id;
}
