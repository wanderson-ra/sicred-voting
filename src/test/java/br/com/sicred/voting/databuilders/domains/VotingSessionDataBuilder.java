package br.com.sicred.voting.databuilders.domains;


import java.util.concurrent.TimeUnit;

import br.com.sicred.voting.databuilders.DataBuilderBase;
import br.com.sicred.voting.databuilders.Utils;
import br.com.sicred.voting.domains.VotingSession;

public class VotingSessionDataBuilder extends DataBuilderBase<VotingSession> {

	private VotingSession votingSession;

	public VotingSessionDataBuilder() {

		this.votingSession = VotingSession.builder()
				.id(this.faker.internet().uuid())
				.ruling(new RulingDataBuilder().build())
				.expiration(Utils.convertToLocalDateTime(this.faker.date().past(1, TimeUnit.DAYS)))
				.createdAt(Utils.convertToLocalDateTime(this.faker.date().past(1, TimeUnit.DAYS)))
				.lastUpdate(Utils.convertToLocalDateTime(this.faker.date().past(1, TimeUnit.DAYS)))
				.build();				
	}	

	public VotingSession build() {
		return this.votingSession;
	}

}
