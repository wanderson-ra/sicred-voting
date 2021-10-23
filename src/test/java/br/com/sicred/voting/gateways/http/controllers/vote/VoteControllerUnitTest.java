package br.com.sicred.voting.gateways.http.controllers.vote;

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
import br.com.sicred.voting.domains.enums.VoteType;
import br.com.sicred.voting.gateways.http.controllers.vote.json.CreateVoteRequestJson;
import br.com.sicred.voting.gateways.http.controllers.vote.json.CreateVoteResponseJson;
import br.com.sicred.voting.usecases.CreateVoteUseCaseOrquestrator;
import br.com.sicred.voting.utils.BaseTest;

public class VoteControllerUnitTest extends BaseTest {
	
	@InjectMocks
	private VoteController voteController;
	
	@Mock
	private CreateVoteUseCaseOrquestrator createVoteUseCaseOrquestrator;
	
	@Test
	@DisplayName("shoul create vote success")
	public void shouldBySuccess() {
		
		final CreateVoteRequestJson createVoteRequestJson = CreateVoteRequestJson.builder()
				.votingSessionId(this.faker.internet().uuid())
				.associateId(this.faker.internet().uuid())
				.voteType(VoteType.NO)
				.build();
		
		final Vote voteCreated = this.domainsDatabuilder.getVolteDataBuilder().build();
		
		when(this.createVoteUseCaseOrquestrator.create(any(Vote.class))).thenReturn(voteCreated);		
		
		final CreateVoteResponseJson response = this.voteController.create(createVoteRequestJson);
		
		final ArgumentCaptor<Vote> voteCaptor = ArgumentCaptor.forClass(Vote.class);
		
		verify(this.createVoteUseCaseOrquestrator, VerificationModeFactory.times(1)).create(voteCaptor.capture());
		
		final Vote voteCaptured = voteCaptor.getValue();		
		
		assertEquals(voteCreated.getId(), response.getId());
				
		assertEquals(createVoteRequestJson.getAssociateId(), voteCaptured.getAssociate().getId());
		assertEquals(createVoteRequestJson.getVotingSessionId(), voteCaptured.getVotingSession().getId());
		assertEquals(createVoteRequestJson.getVoteType(), voteCaptured.getVoteType());
		assertEquals(null, voteCaptured.getId());
		assertEquals(null, voteCaptured.getCreatedAt());		
	}

}
