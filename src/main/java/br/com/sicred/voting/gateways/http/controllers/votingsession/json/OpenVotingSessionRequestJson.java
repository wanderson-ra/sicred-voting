package br.com.sicred.voting.gateways.http.controllers.votingsession.json;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

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
public class OpenVotingSessionRequestJson {
	
	@NotEmpty(message = "Field rulingId is required.")
	private String rulingId;	
	
	private LocalDateTime expiration;
}
