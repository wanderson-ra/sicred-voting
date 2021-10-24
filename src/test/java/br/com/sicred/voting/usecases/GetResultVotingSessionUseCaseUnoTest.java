package br.com.sicred.voting.usecases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.sicred.voting.domains.Result;
import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.domains.enums.VoteType;
import br.com.sicred.voting.domains.enums.Winner;
import br.com.sicred.voting.utils.BaseTest;

public class GetResultVotingSessionUseCaseUnoTest extends BaseTest {
	
	@InjectMocks
	private GetResultVotingSessionUseCase getResultVotingSessionUseCase;
	
	@Mock
	private FindVotingSessionByIdUseCase findVotingSessionByIdUseCase;
	
	@Mock
	private FindVotesByVotingSessionUseCase findVotesByVotingSessionUseCase;
	
	@Test
	@DisplayName("Should by winer yes")
	public void shouldByGetResultWinnerYes() {
		
		final LocalDateTime expiration = LocalDateTime.now().plusDays(1);
		
		final String votingSessionId = faker.internet().uuid();
		
		final VotingSession votingSession = this.domainsDatabuilder.getVotingSessionDataBuilder().setExpiration(expiration).build();
		
		final List<Vote> votesToYes = this.domainsDatabuilder.getVolteDataBuilder().setVoteType(VoteType.YES).buildList(30);
		
		final List<Vote> votesToNo = this.domainsDatabuilder.getVolteDataBuilder().setVoteType(VoteType.NO).buildList(10);
		
		final List<Vote> votes = Stream.concat(votesToYes.stream(), votesToNo.stream()).collect(Collectors.toList());
		
		when(this.findVotingSessionByIdUseCase.find(votingSessionId)).thenReturn(votingSession);
		
		when(this.findVotesByVotingSessionUseCase.find(votingSessionId)).thenReturn(votes);
		
		final Result result = this.getResultVotingSessionUseCase.getResult(votingSessionId);
		
		assertEquals(Winner.YES, result.getWinner());
	}

}
