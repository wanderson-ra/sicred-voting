package br.com.sicred.voting.gateways.database.vote.mongo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;

import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.gateways.database.vote.mongo.repository.VoteRepository;
import br.com.sicred.voting.gateways.exceptions.CreateVoteDatabaseException;
import br.com.sicred.voting.gateways.exceptions.FindAllVotesByVotingSessionDatabaseException;
import br.com.sicred.voting.gateways.exceptions.FindVoteBySessionIdAndAssciateIdAssociateDatabaseException;
import br.com.sicred.voting.utils.BaseTest;

public class VoteDatabaseGatewayImplUniTest extends BaseTest {

	@InjectMocks
	private VoteDatabaseGatewayImpl voteDatabaseGatewayImpl;

	@Mock
	private VoteRepository voteRepository;

	@Test
	@DisplayName("Should by create vote with success")
	public void ShouldBySuccessCreateVote() {
		final Vote voteToCreate = this.domainsDatabuilder.getVolteDataBuilder().toCreate().build();

		final Vote voteCreated = this.domainsDatabuilder.getVolteDataBuilder().build();

		when(this.voteRepository.save(any(Vote.class))).thenReturn(voteCreated);

		final Vote response = this.voteDatabaseGatewayImpl.create(voteToCreate);

		final ArgumentCaptor<Vote> voteCaptor = ArgumentCaptor.forClass(Vote.class);

		verify(this.voteRepository, VerificationModeFactory.times(1)).save(voteCaptor.capture());

		final Vote voteCaptured = voteCaptor.getValue();

		this.assertCreateResponse(voteCreated, response);

		this.assertCreateCaptured(voteToCreate, voteCaptured);
	}

	@Test
	@DisplayName("Should by create vote with error database")
	public void ShouldByErroDatabaseCreateVote() {
		final Vote voteToCreate = this.domainsDatabuilder.getVolteDataBuilder().toCreate().build();

		doThrow(new RuntimeException()).when(this.voteRepository).save(any(Vote.class));

		final CreateVoteDatabaseException error = assertThrows(CreateVoteDatabaseException.class, () -> {
			this.voteDatabaseGatewayImpl.create(voteToCreate);
		});

		assertEquals(error.getCode(), "sicred.vote.database.error.create");
		assertEquals(error.getMessage(), "Error to create Vote.");

	}

	@Test
	@DisplayName("Should by success to find by votingSessionId and associateId")
	public void ShouldSuccessFindByAssociateIdAndVotingSessionId() {

		final String votingSessionId = this.faker.internet().uuid();
		final String associateId = this.faker.internet().uuid();

		final Vote voteFinded = this.domainsDatabuilder.getVolteDataBuilder().build();

		when(this.voteRepository.findByAssociateIdAndVotingSessionId(associateId, votingSessionId))
				.thenReturn(Optional.of(voteFinded));

		final Optional<Vote> optionalVote = this.voteDatabaseGatewayImpl
				.findByAssociateIdAndVotingSessionId(associateId, votingSessionId);

		assertEquals(voteFinded.getAssociate().getId(), optionalVote.get().getAssociate().getId());
		assertEquals(voteFinded.getCreatedAt(), optionalVote.get().getCreatedAt());
		assertEquals(voteFinded.getId(), optionalVote.get().getId());
		assertEquals(voteFinded.getVoteType(), optionalVote.get().getVoteType());
		assertEquals(voteFinded.getVotingSession().getId(), optionalVote.get().getVotingSession().getId());
	}

	@Test
	@DisplayName("Should by error database to find by votingSessionId and associateId")
	public void ShouldErrorDatabaseFindByAssociateIdAndVotingSessionId() {

		final String votingSessionId = this.faker.internet().uuid();
		final String associateId = this.faker.internet().uuid();

		doThrow(new RuntimeException()).when(this.voteRepository).findByAssociateIdAndVotingSessionId(associateId,
				votingSessionId);

		final FindVoteBySessionIdAndAssciateIdAssociateDatabaseException error = assertThrows(
				FindVoteBySessionIdAndAssciateIdAssociateDatabaseException.class, () -> {
					this.voteDatabaseGatewayImpl.findByAssociateIdAndVotingSessionId(associateId, votingSessionId);
				});

		assertEquals(error.getCode(), "sicred.vote.database.error.findbysessionid");
		assertEquals(error.getMessage(), "Error to find Vote.");
	}

	@Test
	@DisplayName("Should by find all by voting session id success")
	public void shouldByFindAllByVotingSessionIdSuccess() {
		final String votingSessionId = this.faker.internet().uuid();

		final List<Vote> votes = this.domainsDatabuilder.getVolteDataBuilder().buildList(10);

		when(this.voteRepository.findAllByVotingSessionId(votingSessionId)).thenReturn(votes);

		final List<Vote> votesResponse = this.voteDatabaseGatewayImpl.findAllByVotingSessionId(votingSessionId);

		assertEquals(votes.get(0).getAssociate().getId(), votesResponse.get(0).getAssociate().getId());
		assertEquals(votes.get(0).getCreatedAt(), votesResponse.get(0).getCreatedAt());
		assertEquals(votes.get(0).getId(), votesResponse.get(0).getId());
		assertEquals(votes.get(0).getVoteType(), votesResponse.get(0).getVoteType());
		assertEquals(votes.get(0).getVotingSession().getId(), votesResponse.get(0).getVotingSession().getId());
	}

	@Test
	@DisplayName("Should by find all by voting session id error database")
	public void shouldByFindAllByVotingSessionIdErrorDatabase() {
		final String votingSessionId = this.faker.internet().uuid();

		doThrow(new RuntimeException()).when(this.voteRepository).findAllByVotingSessionId(votingSessionId);

		final FindAllVotesByVotingSessionDatabaseException error = assertThrows(
				FindAllVotesByVotingSessionDatabaseException.class, () -> {
					this.voteDatabaseGatewayImpl.findAllByVotingSessionId(votingSessionId);
				});

		assertEquals(error.getCode(), "sicred.vote.database.error.findallbyvotingsession");
		assertEquals(error.getMessage(), "Error to find all by voting session.");

	}

	private void assertCreateCaptured(final Vote voteToCreate, final Vote voteCaptured) {
		assertEquals(voteToCreate.getAssociate().getId(), voteCaptured.getAssociate().getId());
		assertEquals(voteToCreate.getVotingSession().getId(), voteCaptured.getVotingSession().getId());
		assertEquals(voteToCreate.getVoteType(), voteCaptured.getVoteType());
		assertEquals(null, voteCaptured.getId());
		assertEquals(null, voteCaptured.getCreatedAt());
	}

	private void assertCreateResponse(final Vote voteCreated, final Vote response) {
		assertEquals(voteCreated.getAssociate().getId(), response.getAssociate().getId());
		assertEquals(voteCreated.getVotingSession().getId(), response.getVotingSession().getId());
		assertEquals(voteCreated.getVoteType(), response.getVoteType());
		assertEquals(voteCreated.getId(), response.getId());
		assertEquals(voteCreated.getCreatedAt(), response.getCreatedAt());
	}

}
