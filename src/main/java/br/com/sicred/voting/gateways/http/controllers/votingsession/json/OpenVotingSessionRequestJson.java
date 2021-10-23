package br.com.sicred.voting.gateways.http.controllers.votingsession.json;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class OpenVotingSessionRequestJson {
	
	@NotEmpty(message = "Field rulingId is required.")
	private String rulingId;	
	
	private LocalDateTime expiration;
}
