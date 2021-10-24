package br.com.sicred.voting.gateways.http.controllers.vote.json;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.sicred.voting.domains.enums.VoteType;
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
public class CreateVoteRequestJson {

    @NotNull(message = "Field voteType is required.")
	private VoteType voteType;

	@NotEmpty(message = "Field associateId is required.")
	private String associateId;

	@NotEmpty(message = "Field votingSessionId is required.")
	private String votingSessionId;

}
