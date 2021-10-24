package br.com.sicred.voting.usecases;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.gateways.database.vote.VoteDatabaseGateway;
import br.com.sicred.voting.utils.BaseTest;

public class FindVotesByVotingSessionUseCaseUniTest extends BaseTest {
	
	@InjectMocks
	private FindVotesByVotingSessionUseCase findVotesByVotingSessionUseCase;
	
	@Mock
	private VoteDatabaseGateway voteDatabaseGateway;
	
	
	@Test
	@DisplayName("Should by success find list")
	public void ShouldByFindWithSuccess() {
		final String votingSessionId = this.faker.internet().uuid();
		
		final List<Vote> votes = this.domainsDatabuilder.getVolteDataBuilder().buildList(10);
		
		when(this.voteDatabaseGateway.findAllByVotingSessionId(votingSessionId)).thenReturn(votes);		
		
		final List<Vote> votesResponse = this.findVotesByVotingSessionUseCase.find(votingSessionId);
		
		assertEquals(votes.get(0).getAssociate().getId(), votesResponse.get(0).getAssociate().getId());
		assertEquals(votes.get(0).getCreatedAt(), votesResponse.get(0).getCreatedAt());
		assertEquals(votes.get(0).getId(), votesResponse.get(0).getId());
		assertEquals(votes.get(0).getVoteType(), votesResponse.get(0).getVoteType());
		assertEquals(votes.get(0).getVotingSession().getId(), votesResponse.get(0).getVotingSession().getId());
	}

}
