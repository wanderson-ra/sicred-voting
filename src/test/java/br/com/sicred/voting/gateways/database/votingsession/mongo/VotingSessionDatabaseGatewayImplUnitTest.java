package br.com.sicred.voting.gateways.database.votingsession.mongo;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.gateways.database.votingsession.mongo.repository.VotingSessionRepository;
import br.com.sicred.voting.gateways.exceptions.FindByIdDatabaseException;
import br.com.sicred.voting.gateways.exceptions.OpenVotingSessionDatabaseException;
import br.com.sicred.voting.utils.BaseTest;

public class VotingSessionDatabaseGatewayImplUnitTest extends BaseTest {

	@InjectMocks
	private VotingSessionDatabaseGatewayImpl votingSessionDatabaseGatewayImpl;

	@Mock
	private VotingSessionRepository votingSessionRepository;

	@Test
	@DisplayName("Should by found success")
	public void shouldByFindByIdWithSuccess() {

		final VotingSession votingSessionFinded = this.domainsDatabuilder.getVotingSessionDataBuilder().build();

		final String votingSessionId = this.faker.internet().uuid();

		when(this.votingSessionRepository.findById(votingSessionId)).thenReturn(Optional.of(votingSessionFinded));

		final Optional<VotingSession> votingSessionFindedResponse = this.votingSessionDatabaseGatewayImpl
				.findById(votingSessionId);

		assertEquals(votingSessionFinded.getCreatedAt(), votingSessionFindedResponse.get().getCreatedAt());
		assertEquals(votingSessionFinded.getExpiration(), votingSessionFindedResponse.get().getExpiration());
		assertEquals(votingSessionFinded.getId(), votingSessionFindedResponse.get().getId());
		assertEquals(votingSessionFinded.getLastUpdate(), votingSessionFindedResponse.get().getLastUpdate());
		assertEquals(votingSessionFinded.getRuling().getId(), votingSessionFindedResponse.get().getRuling().getId());
	}

	@Test
	@DisplayName("Should by error database")
	public void shouldByFindByIdWithDatabaseError() {

		final String votingSessionId = this.faker.internet().uuid();

		doThrow(new RuntimeException()).when(this.votingSessionRepository).findById(votingSessionId);

		final FindByIdDatabaseException error = assertThrows(FindByIdDatabaseException.class, () -> {
			this.votingSessionDatabaseGatewayImpl.findById(votingSessionId);
		});

		assertEquals(error.getCode(), "sicred.votingsession.database.error.findbyid");
		assertEquals(error.getMessage(), "Error to find by id.");
	}

	@Test
	@DisplayName("Should by open with success")
	public void shouldByOpenWithSuccess() {

		final VotingSession votingSessionToOpen = this.domainsDatabuilder.getVotingSessionDataBuilder()
				.toOpenDontExpirationDefault().build();

		final VotingSession votingSessionOpened = this.domainsDatabuilder.getVotingSessionDataBuilder().build();

		when(this.votingSessionRepository.save(any(VotingSession.class))).thenReturn(votingSessionOpened);

		final VotingSession votingSessionOpenedResponse = this.votingSessionDatabaseGatewayImpl
				.open(votingSessionToOpen);

		final ArgumentCaptor<VotingSession> votingSessionCaptor = ArgumentCaptor.forClass(VotingSession.class);

		verify(this.votingSessionRepository, VerificationModeFactory.times(1)).save(votingSessionCaptor.capture());

		final VotingSession votingSessionCaptured = votingSessionCaptor.getValue();

		this.assertResponseToOpen(votingSessionOpened, votingSessionOpenedResponse);

		this.assertCapturedToOpen(votingSessionToOpen, votingSessionCaptured);

	}

	@Test
	@DisplayName("Should by open with error database")
	public void shouldByOpenWithErrorDatabase() {

		final VotingSession votingSessionToOpen = this.domainsDatabuilder.getVotingSessionDataBuilder()
				.toOpenDontExpirationDefault().build();

		doThrow(new RuntimeException()).when(this.votingSessionRepository).save(any(VotingSession.class));

		final OpenVotingSessionDatabaseException error = assertThrows(OpenVotingSessionDatabaseException.class, () -> {
			this.votingSessionDatabaseGatewayImpl.open(votingSessionToOpen);
		});

		assertEquals(error.getCode(), "sicred.votingsession.database.error.open");
		assertEquals(error.getMessage(), "Error to open Voting Session.");

	}

	private void assertCapturedToOpen(final VotingSession votingSessionToOpen,
			final VotingSession votingSessionCaptured) {
		assertEquals(votingSessionToOpen.getExpiration(), votingSessionCaptured.getExpiration());
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
