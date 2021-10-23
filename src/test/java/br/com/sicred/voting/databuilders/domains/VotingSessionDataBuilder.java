package br.com.sicred.voting.databuilders.domains;


import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.time.LocalDateTime;
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
	
	public VotingSessionDataBuilder toOpenExpirationDefault() {
		setField(this.votingSession, "expiration", null);
		setField(this.votingSession, "id", null);	
		setField(this.votingSession, "createdAt", null);			
		setField(this.votingSession, "lastUpdate", null);	
		return this;
	}
	
	public VotingSessionDataBuilder toOpenDontExpirationDefault() {
		setField(this.votingSession, "expiration", Utils.convertToLocalDateTime(this.faker.date().past(1, TimeUnit.DAYS)));	
		setField(this.votingSession, "id", null);	
		setField(this.votingSession, "createdAt", null);			
		setField(this.votingSession, "lastUpdate", null);	
		return this;
	}
	
	public VotingSessionDataBuilder setExpiration(final LocalDateTime localDateTime) {
		setField(this.votingSession, "expiration", localDateTime);				
		return this;
	}

	public VotingSession build() {
		return this.votingSession;
	}

}
