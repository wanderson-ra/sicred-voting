package br.com.sicred.voting.databuilders.domains;

import java.util.concurrent.TimeUnit;

import br.com.sicred.voting.databuilders.DataBuilderBase;
import br.com.sicred.voting.databuilders.Utils;
import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.domains.enums.VoteType;

public class VolteDataBuilder extends DataBuilderBase<Vote> {

	private Vote vote;

	public VolteDataBuilder() {

		this.vote = Vote.builder()
				.id(this.faker.internet().uuid())
				.voteType(VoteType.YES)
				.associate(new AssociateDataBuilder().build())
				.votingSession(new VotingSessionDataBuilder().build())
				.createdAt(Utils.convertToLocalDateTime(this.faker.date().past(1, TimeUnit.DAYS)))
				.build();
	}

	public Vote build() {
		return this.vote;
	}

}
