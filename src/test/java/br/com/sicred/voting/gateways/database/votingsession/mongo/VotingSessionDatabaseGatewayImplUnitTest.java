package br.com.sicred.voting.gateways.database.votingsession.mongo;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.doThrow;

import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.gateways.database.votingsession.mongo.repository.VotingSessionRepository;
import br.com.sicred.voting.gateways.exceptions.FindByIdDatabaseException;
import br.com.sicred.voting.utils.BaseTest;

public class VotingSessionDatabaseGatewayImplUnitTest extends BaseTest {

	@InjectMocks
	private VotingSessionDatabaseGatewayImpl votingSessionDatabaseGatewayImpl;

	@Mock
	private VotingSessionRepository votingSessionRepository;

	@Test
	@DisplayName("Should by found success")
	public void findByIdWithSuccess() {

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
	public void findByIdWithDatabaseError() {

		final String votingSessionId = this.faker.internet().uuid();

		doThrow(new RuntimeException()).when(this.votingSessionRepository).findById(votingSessionId);

	
		final FindByIdDatabaseException error = assertThrows(FindByIdDatabaseException.class, () -> {
			this.votingSessionDatabaseGatewayImpl.findById(votingSessionId);
		});

		assertEquals(error.getCode(), "sicred.votingsession.database.error.findbyid");
		assertEquals(error.getMessage(), "Error to find by id.");
	}

}
