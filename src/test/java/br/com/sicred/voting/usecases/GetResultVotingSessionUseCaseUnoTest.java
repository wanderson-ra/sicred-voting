package br.com.sicred.voting.usecases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.sicred.voting.domains.Result;
import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.domains.enums.VoteType;
import br.com.sicred.voting.domains.enums.Winner;
import br.com.sicred.voting.usecases.exceptions.VotingSessionIsStillOpenException;
import br.com.sicred.voting.utils.BaseTest;

public class GetResultVotingSessionUseCaseUnoTest extends BaseTest {

	@InjectMocks
	private GetResultVotingSessionUseCase getResultVotingSessionUseCase;

	@Mock
	private FindVotingSessionByIdUseCase findVotingSessionByIdUseCase;

	@Mock
	private FindVotesByVotingSessionUseCase findVotesByVotingSessionUseCase;

	@Test
	@DisplayName("Should by winner yes")
	public void shouldByGetResultWinnerYes() {

		final Integer totalYes = 30;
		final Integer totalNo = 10;

		final LocalDateTime expiration = LocalDateTime.now().plusDays(1);

		final String votingSessionId = faker.internet().uuid();

		final VotingSession votingSession = this.domainsDatabuilder.getVotingSessionDataBuilder()
				.setExpiration(expiration).build();

		final List<Vote> votesToYes = this.domainsDatabuilder.getVolteDataBuilder().setVoteType(VoteType.YES)
				.buildList(totalYes);

		final List<Vote> votesToNo = this.domainsDatabuilder.getVolteDataBuilder().setVoteType(VoteType.NO)
				.buildList(totalNo);

		final List<Vote> votes = Stream.concat(votesToYes.stream(), votesToNo.stream()).collect(Collectors.toList());

		when(this.findVotingSessionByIdUseCase.find(votingSessionId)).thenReturn(votingSession);

		when(this.findVotesByVotingSessionUseCase.find(votingSessionId)).thenReturn(votes);

		final Result result = this.getResultVotingSessionUseCase.getResult(votingSessionId);

		assertEquals(Winner.YES, result.getWinner());
		assertEquals(totalNo, result.getNo());
		assertEquals(totalYes, result.getYes());
	}

	@Test
	@DisplayName("Should by winner no")
	public void shouldByGetResultWinnerNo() {

		final Integer totalYes = 7;
		final Integer totalNo = 10;

		final LocalDateTime expiration = LocalDateTime.now().plusDays(1);

		final String votingSessionId = faker.internet().uuid();

		final VotingSession votingSession = this.domainsDatabuilder.getVotingSessionDataBuilder()
				.setExpiration(expiration).build();

		final List<Vote> votesToYes = this.domainsDatabuilder.getVolteDataBuilder().setVoteType(VoteType.YES)
				.buildList(totalYes);

		final List<Vote> votesToNo = this.domainsDatabuilder.getVolteDataBuilder().setVoteType(VoteType.NO)
				.buildList(totalNo);

		final List<Vote> votes = Stream.concat(votesToYes.stream(), votesToNo.stream()).collect(Collectors.toList());

		when(this.findVotingSessionByIdUseCase.find(votingSessionId)).thenReturn(votingSession);

		when(this.findVotesByVotingSessionUseCase.find(votingSessionId)).thenReturn(votes);

		final Result result = this.getResultVotingSessionUseCase.getResult(votingSessionId);

		assertEquals(Winner.NO, result.getWinner());
		assertEquals(totalNo, result.getNo());
		assertEquals(totalYes, result.getYes());
	}

	@Test
	@DisplayName("Should by tie")
	public void shouldByGetResultWinnerTie() {

		final Integer totalYes = 10;
		final Integer totalNo = 10;

		final LocalDateTime expiration = LocalDateTime.now().plusDays(1);

		final String votingSessionId = faker.internet().uuid();

		final VotingSession votingSession = this.domainsDatabuilder.getVotingSessionDataBuilder()
				.setExpiration(expiration).build();

		final List<Vote> votesToYes = this.domainsDatabuilder.getVolteDataBuilder().setVoteType(VoteType.YES)
				.buildList(totalYes);

		final List<Vote> votesToNo = this.domainsDatabuilder.getVolteDataBuilder().setVoteType(VoteType.NO)
				.buildList(totalNo);

		final List<Vote> votes = Stream.concat(votesToYes.stream(), votesToNo.stream()).collect(Collectors.toList());

		when(this.findVotingSessionByIdUseCase.find(votingSessionId)).thenReturn(votingSession);

		when(this.findVotesByVotingSessionUseCase.find(votingSessionId)).thenReturn(votes);

		final Result result = this.getResultVotingSessionUseCase.getResult(votingSessionId);

		assertEquals(Winner.TIE, result.getWinner());
		assertEquals(totalNo, result.getNo());
		assertEquals(totalYes, result.getYes());
	}

	@Test
	@DisplayName("Should by error voting session open")
	public void shouldByGetResultErrorSessionOpen() {

		final LocalDateTime expiration = LocalDateTime.now().minusDays(1);

		final String votingSessionId = faker.internet().uuid();

		final VotingSession votingSession = this.domainsDatabuilder.getVotingSessionDataBuilder()
				.setExpiration(expiration).build();

		when(this.findVotingSessionByIdUseCase.find(votingSessionId)).thenReturn(votingSession);

		
		final VotingSessionIsStillOpenException error = assertThrows(
				VotingSessionIsStillOpenException.class, () -> {
					this.getResultVotingSessionUseCase.getResult(votingSessionId);
				});

		assertEquals(error.getCode(), "sicred.votingsession.error.isstillopen");
		assertEquals(error.getMessage(), "Voting Session is still open.");

	}

}
