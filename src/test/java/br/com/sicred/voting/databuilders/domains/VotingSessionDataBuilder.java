package br.com.sicred.voting.databuilders.domains;


import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import br.com.sicred.voting.databuilders.DataBuilderBase;
import br.com.sicred.voting.databuilders.Utils;
import br.com.sicred.voting.domains.VotingSession;

public class VotingSessionDataBuilder extends DataBuilderBase<VotingSession> {

	private LocalDateTime expiration;
	private String id;
	private LocalDateTime lastUpdate;
	private LocalDateTime createdAt;

	public VotingSessionDataBuilder() {
		this.id = this.faker.internet().uuid();
		this.createdAt = Utils.convertToLocalDateTime(this.faker.date().past(2, TimeUnit.DAYS));
		this.lastUpdate = Utils.convertToLocalDateTime(this.faker.date().past(2, TimeUnit.DAYS));	
		this.expiration = Utils.convertToLocalDateTime(this.faker.date().past(1, TimeUnit.DAYS));				
	}	
	
	public VotingSessionDataBuilder toOpenExpirationDefault() {
		this.expiration = null;
		this.id = null;
		this.lastUpdate = null;
		this.createdAt = null;
		
		return this;
	}
	
	public VotingSessionDataBuilder toOpenDontExpirationDefault() {
		this.expiration = Utils.convertToLocalDateTime(this.faker.date().past(1, TimeUnit.DAYS));	
		this.id = null;
		this.lastUpdate = null;
		this.createdAt = null;	
		return this;
	}
	
	public VotingSessionDataBuilder setExpiration(final LocalDateTime expiration) {
		this.expiration = expiration;
					
		return this;
	}

	public VotingSession build() {
		return VotingSession.builder()
				.id(this.id)
				.ruling(new RulingDataBuilder().build())			
				.expiration(this.expiration)
				.createdAt(this.createdAt)
				.lastUpdate(this.lastUpdate)
				.build();		
	}

}
