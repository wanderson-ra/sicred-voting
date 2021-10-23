package br.com.sicred.voting.usecases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.gateways.database.votingsession.VotingSessionDatabaseGateway;
import br.com.sicred.voting.usecases.exceptions.VotingSessionIsClosedException;
import br.com.sicred.voting.utils.BaseTest;

public class FindVotingSessionByIdUseCaseUniTest extends BaseTest {

	@InjectMocks
	private FindVotingSessionByIdUseCase findVotingSessionByIdUseCase;

	@Mock
	private VotingSessionDatabaseGateway votingSessionDatabaseGateway;

	@Test
	@DisplayName("Should by found votting session")
	public void ShouldByFound() {

		final VotingSession votingSessionFinded = this.domainsDatabuilder.getVotingSessionDataBuilder().build();

		final String votingSessionId = this.faker.internet().uuid();

		when(this.votingSessionDatabaseGateway.findById(votingSessionId)).thenReturn(Optional.of(votingSessionFinded));

		final VotingSession votingSessionFindedResponse = this.findVotingSessionByIdUseCase.find(votingSessionId);

		assertEquals(votingSessionFinded.getCreatedAt(), votingSessionFindedResponse.getCreatedAt());
		assertEquals(votingSessionFinded.getExpiration(), votingSessionFindedResponse.getExpiration());
		assertEquals(votingSessionFinded.getId(), votingSessionFindedResponse.getId());
		assertEquals(votingSessionFinded.getLastUpdate(), votingSessionFindedResponse.getLastUpdate());
		assertEquals(votingSessionFinded.getRuling().getId(), votingSessionFindedResponse.getRuling().getId());
	}

	@Test
	@DisplayName("Should by not found votting session")
	public void ShouldByNotFound() {

		final String votingSessionId = this.faker.internet().uuid();

		when(this.votingSessionDatabaseGateway.findById(votingSessionId)).thenReturn(Optional.empty());

		final VotingSessionIsClosedException error = assertThrows(VotingSessionIsClosedException.class, () -> {
			this.findVotingSessionByIdUseCase.find(votingSessionId);
		});

		assertEquals(error.getCode(), "sicred.votingsession.error.closed");
		assertEquals(error.getMessage(), "Voting Session is closed.");
	}

}
