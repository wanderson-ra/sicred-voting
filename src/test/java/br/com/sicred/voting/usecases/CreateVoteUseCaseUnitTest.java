package br.com.sicred.voting.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.gateways.database.vote.VoteDatabaseGateway;
import br.com.sicred.voting.utils.BaseTest;

public class CreateVoteUseCaseUnitTest extends BaseTest {
	
	@InjectMocks
	private CreateVoteUseCase createVoteUseCase;
	
	@Mock
	private VoteDatabaseGateway voteDatabaseGateway;
	
	@Test
	@DisplayName("should by create vote success")
	public void shouldBySuccess() {

		final Vote voteToCreate = this.domainsDatabuilder.getVolteDataBuilder().toCreate().build();

		final Vote voteCreated = this.domainsDatabuilder.getVolteDataBuilder().toCreate().build();

		when(this.voteDatabaseGateway.create(any(Vote.class))).thenReturn(voteCreated);

		final Vote response = this.createVoteUseCase.create(voteToCreate);
		
		final ArgumentCaptor<Vote> voteCaptor = ArgumentCaptor.forClass(Vote.class);
		
		verify(this.voteDatabaseGateway, VerificationModeFactory.times(1)).create(voteCaptor.capture());
		
		final Vote voteCaptured = voteCaptor.getValue();
		
		this.assertVoteCaptured(voteToCreate, voteCaptured);
		
		this.assertVoteResponse(voteCreated, response);	
	}

	private void assertVoteResponse(final Vote voteCreated, final Vote response) {
		assertEquals(voteCreated.getAssociate().getId(), response.getAssociate().getId());
		assertEquals(voteCreated.getCreatedAt(), response.getCreatedAt());
		assertEquals(voteCreated.getId(), response.getId());
		assertEquals(voteCreated.getVoteType(), response.getVoteType());
		assertEquals(voteCreated.getVotingSession().getId(), response.getVotingSession().getId());
	}

	private void assertVoteCaptured(final Vote voteToCreate, final Vote voteCaptured) {
		assertEquals(voteToCreate.getAssociate().getId(), voteCaptured.getAssociate().getId());
		assertEquals(voteToCreate.getVotingSession().getId(), voteCaptured.getVotingSession().getId());
		assertEquals(voteToCreate.getVoteType(), voteCaptured.getVoteType());
		assertEquals(null, voteCaptured.getId());
		assertEquals(null, voteCaptured.getCreatedAt());
	}

}
