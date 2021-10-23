package br.com.sicred.voting.usecases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.gateways.database.vote.VoteDatabaseGateway;
import br.com.sicred.voting.usecases.exceptions.AssociateAlreadyVotedInThisSessionException;
import br.com.sicred.voting.utils.BaseTest;

public class CheckAssociateAlreadyVotedUseCaseUnitTest extends BaseTest {

	@InjectMocks
	private CheckAssociateAlreadyVotedUseCase checkAssociateAlreadyVotedUseCase;

	@Mock
	private VoteDatabaseGateway voteDatabaseGateway;

	@Test
	@DisplayName("should by check associate dont voted")
	public void shouldBySuccess() {

		final String votingSessionId = this.faker.internet().uuid();
		final String associateId = this.faker.internet().uuid();

		when(this.voteDatabaseGateway.findByAssociateIdAndVotingSessionId(votingSessionId, associateId))
				.thenReturn(Optional.empty());

		this.checkAssociateAlreadyVotedUseCase.check(votingSessionId, associateId);

		verify(this.voteDatabaseGateway, VerificationModeFactory.times(1))
				.findByAssociateIdAndVotingSessionId(votingSessionId, associateId);

	}

	@Test
	@DisplayName("should by check associate already voted")
	public void shouldByErrorAssociateAlreadyVoted() {

		final Vote vote = this.domainsDatabuilder.getVolteDataBuilder().build();

		final String votingSessionId = this.faker.internet().uuid();
		final String associateId = this.faker.internet().uuid();

		when(this.voteDatabaseGateway.findByAssociateIdAndVotingSessionId(votingSessionId, associateId))
				.thenReturn(Optional.of(vote));

		final AssociateAlreadyVotedInThisSessionException error = assertThrows(AssociateAlreadyVotedInThisSessionException.class, () -> {
			this.checkAssociateAlreadyVotedUseCase.check(votingSessionId, associateId);
		});

		assertEquals(error.getCode(), "sicred.vote.error.associateAlreadyVoted");
		assertEquals(error.getMessage(), "Associate  already voted in this session.");

	}

}
