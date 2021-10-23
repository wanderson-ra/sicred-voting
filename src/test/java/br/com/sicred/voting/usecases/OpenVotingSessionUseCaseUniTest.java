package br.com.sicred.voting.usecases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.gateways.database.votingsession.VotingSessionDatabaseGateway;
import br.com.sicred.voting.utils.BaseTest;

public class OpenVotingSessionUseCaseUniTest extends BaseTest {
	
	@InjectMocks
	private OpenVotingSessionUseCase openVotingSessionUseCase;
	
	@Mock
	private VotingSessionDatabaseGateway votingSessionDatabaseGateway;
	
	
	@Test
	@DisplayName("Should by open voting session with exiration default")
	public void ShoulbByOpenExpirationDefault() {
		
		 final VotingSession votingSessionToOpen = this.domainsDatabuilder.getVotingSessionDataBuilder().toOpenExpirationDefault().build();
		 
		 final VotingSession votingSessionOpened = this.domainsDatabuilder.getVotingSessionDataBuilder().build();
		 
		 when(this.votingSessionDatabaseGateway.open(any(VotingSession.class))).thenReturn(votingSessionOpened);
		 
		 final VotingSession votingSessionOpenedResponse = this.openVotingSessionUseCase.open(votingSessionToOpen);
		 
		 final ArgumentCaptor<VotingSession> votingSessionCaptor = ArgumentCaptor.forClass(VotingSession.class);
		 
		 verify(this.votingSessionDatabaseGateway, VerificationModeFactory.times(1)).open(votingSessionCaptor.capture());
		 
		 final VotingSession votingSessionCaptured = votingSessionCaptor.getValue();
		 
		 this.assertResponseToOpen(votingSessionOpened, votingSessionOpenedResponse);
		 
		 this.assertCapturedToOpen(votingSessionToOpen, votingSessionCaptured);
	}
	
	
	@Test
	@DisplayName("Should by open voting session with dont exiration default")
	public void ShoulbByOpenExpirationDontDefault() {
		
		 final VotingSession votingSessionToOpen = this.domainsDatabuilder.getVotingSessionDataBuilder().toOpenDontExpirationDefault().build();
		 
		 final VotingSession votingSessionOpened = this.domainsDatabuilder.getVotingSessionDataBuilder().build();
		 
		 when(this.votingSessionDatabaseGateway.open(any(VotingSession.class))).thenReturn(votingSessionOpened);
		 
		 final VotingSession votingSessionOpenedResponse = this.openVotingSessionUseCase.open(votingSessionToOpen);
		 
		 final ArgumentCaptor<VotingSession> votingSessionCaptor = ArgumentCaptor.forClass(VotingSession.class);
		 
		 verify(this.votingSessionDatabaseGateway, VerificationModeFactory.times(1)).open(votingSessionCaptor.capture());
		 
		 final VotingSession votingSessionCaptured = votingSessionCaptor.getValue();
		 
		 this.assertResponseToOpen(votingSessionOpened, votingSessionOpenedResponse);
		 
		 assertEquals(votingSessionToOpen.getExpiration(), votingSessionCaptured.getExpiration());
		 assertEquals(votingSessionToOpen.getRuling().getId(), votingSessionCaptured.getRuling().getId());
		 assertEquals(null, votingSessionCaptured.getId());
		 assertEquals(null, votingSessionCaptured.getCreatedAt());
		 assertEquals(null, votingSessionCaptured.getLastUpdate());
	}
	

	private void assertCapturedToOpen(final VotingSession votingSessionToOpen,
			final VotingSession votingSessionCaptured) {
		assertNotEquals(votingSessionToOpen.getExpiration(), votingSessionCaptured.getExpiration());		 
		 assertEquals(votingSessionToOpen.getRuling().getId(), votingSessionCaptured.getRuling().getId());
		 assertEquals(null, votingSessionCaptured.getId());
		 assertEquals(null, votingSessionCaptured.getCreatedAt());
		 assertEquals(null, votingSessionCaptured.getLastUpdate());
	}
	
	private void assertResponseToOpen(final VotingSession votingSessionOpened,
			final VotingSession votingSessionOpenedResponse) {
		assertEquals(votingSessionOpened.getExpiration(), votingSessionOpenedResponse.getExpiration());		
		 assertEquals(votingSessionOpened.getRuling().getId(), votingSessionOpenedResponse.getRuling().getId());
		 assertEquals(votingSessionOpened.getId(), votingSessionOpenedResponse.getId());
		 assertEquals(votingSessionOpened.getCreatedAt(), votingSessionOpenedResponse.getCreatedAt());
		 assertEquals(votingSessionOpened.getLastUpdate(), votingSessionOpenedResponse.getLastUpdate());
	}

}
