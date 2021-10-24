package br.com.sicred.voting.databuilders.domains;


import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import br.com.sicred.voting.databuilders.DataBuilderBase;
import br.com.sicred.voting.databuilders.Utils;
import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.domains.enums.VoteType;

public class VoteDataBuilder extends DataBuilderBase<Vote> {

	private String id;
	private VoteType voteType;
	private LocalDateTime createdAt;

	public VoteDataBuilder() {
		this.id = faker.internet().uuid();
		this.createdAt = Utils.convertToLocalDateTime(this.faker.date().past(1, TimeUnit.DAYS));
		this.voteType = VoteType.NO;		
	}
	
	public VoteDataBuilder toCreate() {
		this.id = null;
		this.createdAt = null;
				
		return this;
	}
	
	public VoteDataBuilder setVoteType(final VoteType voteType) {
		this.voteType = voteType;				
		return this;
	}

	public Vote build() {
		return Vote.builder()
				.id(this.id)
				.voteType(this.voteType)
				.associate(new AssociateDataBuilder().build())
				.votingSession(new VotingSessionDataBuilder().build())
				.createdAt(this.createdAt)
				.build();
	}

}
