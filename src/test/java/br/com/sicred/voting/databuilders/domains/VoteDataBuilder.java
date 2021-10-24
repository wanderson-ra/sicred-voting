package br.com.sicred.voting.databuilders.domains;

import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.concurrent.TimeUnit;

import br.com.sicred.voting.databuilders.DataBuilderBase;
import br.com.sicred.voting.databuilders.Utils;
import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.domains.enums.VoteType;

public class VoteDataBuilder extends DataBuilderBase<Vote> {

	private Vote vote;

	public VoteDataBuilder() {

		this.vote = Vote.builder()
				.id(this.faker.internet().uuid())
				.voteType(VoteType.YES)
				.associate(new AssociateDataBuilder().build())
				.votingSession(new VotingSessionDataBuilder().build())
				.createdAt(Utils.convertToLocalDateTime(this.faker.date().past(1, TimeUnit.DAYS)))
				.build();
	}
	
	public VoteDataBuilder toCreate() {
		setField(this.vote, "id", null);
		setField(this.vote, "createdAt", null);	
		
		return this;
	}
	
	public VoteDataBuilder setVoteType(final VoteType voteType) {
		setField(this.vote, "voteType", voteType);
				
		return this;
	}

	public Vote build() {
		return this.vote;
	}

}