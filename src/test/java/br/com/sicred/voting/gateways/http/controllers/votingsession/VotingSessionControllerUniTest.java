package br.com.sicred.voting.gateways.http.controllers.votingsession;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.sicred.voting.databuilders.Utils;
import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.gateways.http.controllers.votingsession.json.OpenVotingSessionRequestJson;
import br.com.sicred.voting.gateways.http.controllers.votingsession.json.OpenVotingSessionResponseJson;
import br.com.sicred.voting.usecases.OpenVotingSessionUseCase;
import br.com.sicred.voting.utils.BaseTest;

public class VotingSessionControllerUniTest extends BaseTest{
	
	@InjectMocks
	private VotingSessionController votingSessionController;
	
	@Mock
	private OpenVotingSessionUseCase openVotingSessionUseCase;
	
	
	@Test
	@DisplayName("Should by open voting session with success")
	public void ShouldByOpenSessionSuccess() {
		
		final OpenVotingSessionRequestJson openVotingSessionRequestJson = OpenVotingSessionRequestJson
				.builder()
				.expiration(Utils.convertToLocalDateTime(this.faker.date().future(1, TimeUnit.DAYS)))
				.rulingId(this.faker.internet().uuid())
				.build();
		
		final VotingSession votingSessionOpened = this.domainsDatabuilder.getVotingSessionDataBuilder().build();
		
		when(this.openVotingSessionUseCase.open(any(VotingSession.class))).thenReturn(votingSessionOpened);
		
		final OpenVotingSessionResponseJson response = this.votingSessionController.openVotingSession(openVotingSessionRequestJson);
				
		final ArgumentCaptor<VotingSession> votingSessionCaptor = ArgumentCaptor.forClass(VotingSession.class);
		
		verify(this.openVotingSessionUseCase, VerificationModeFactory.times(1)).open(votingSessionCaptor.capture());
		
		final VotingSession votingSessionCaptured = votingSessionCaptor.getValue();		
		
		assertEquals(votingSessionOpened.getId(), response.getId());		
		
		assertEquals(openVotingSessionRequestJson.getExpiration(), votingSessionCaptured.getExpiration());
		assertEquals(openVotingSessionRequestJson.getRulingId(), votingSessionCaptured.getRuling().getId());
		assertEquals(null, votingSessionCaptured.getId());
		assertEquals(null, votingSessionCaptured.getCreatedAt());
		assertEquals(null, votingSessionCaptured.getLastUpdate());
	}
}
