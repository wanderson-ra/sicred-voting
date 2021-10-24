package br.com.sicred.voting.usecases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Result;
import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.domains.enums.VoteType;
import br.com.sicred.voting.domains.enums.Winner;
import br.com.sicred.voting.usecases.exceptions.VotingSessionIsStillOpenException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetResultVotingSessionUseCase {

	@Autowired
	private FindVotingSessionByIdUseCase findVotingSessionByIdUseCase;

	@Autowired
	private FindVotesByVotingSessionUseCase findVotesByVotingSessionUseCase;
	
	

	public Result getResult(final String votingSessionId) {
		log.info("votingSessionId: {}", votingSessionId);

		final VotingSession votingSessionFinded = this.findVotingSessionByIdUseCase.find(votingSessionId);

		this.checkVotingSessionIsStillOpen(votingSessionFinded);

		final List<Vote> votesBySession = this.findVotesByVotingSessionUseCase.find(votingSessionId);

		final List<Vote> totalVotesNo = votesBySession.stream().filter(vote -> vote.getVoteType() == VoteType.NO).collect(Collectors.toList());
		final List<Vote> totalVotesYes = votesBySession.stream().filter(vote -> vote.getVoteType() == VoteType.YES).collect(Collectors.toList());

		final Winner winner = totalVotesNo.size() == totalVotesYes.size() ? Winner.TIE
				: totalVotesNo.size() > totalVotesYes.size()  ? Winner.NO : Winner.YES;

		return Result.builder()
				.yes(totalVotesYes.size())
				.no(totalVotesNo.size())
				.winner(winner).build();
	}

	private void checkVotingSessionIsStillOpen(final VotingSession votingSessionFinded) {
		if (!votingSessionFinded.isOpen()) {
			throw new VotingSessionIsStillOpenException();
		}
	}

}
