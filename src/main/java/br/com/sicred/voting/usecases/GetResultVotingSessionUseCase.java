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

		final Integer totalVotesNo = this.getTotalVotes(votesBySession, VoteType.NO);
		
		final Integer totalVotesYes = this.getTotalVotesYes(votesBySession, totalVotesNo);

		final Winner winner = this.getWinner(totalVotesNo, totalVotesYes);				

		return Result.builder()
				.yes(totalVotesYes)
				.no(totalVotesNo)
				.winner(winner).build();
	}

	private Integer getTotalVotesYes(final List<Vote> votesBySession, final Integer totalVotesNo) {
		final Integer totalVotesYes = votesBySession.size() - totalVotesNo;
		return totalVotesYes;
	}

	private Winner getWinner(final Integer totalVotesNo, final Integer totalVotesYes) {
		final Winner winner = totalVotesNo == totalVotesYes ? Winner.TIE
				: totalVotesNo > totalVotesYes  ? Winner.NO : Winner.YES;
		return winner;
	}

	private Integer getTotalVotes(final List<Vote> votesBySession, final VoteType voteType) {
		final Integer totalVotesNo = votesBySession.stream().filter(vote -> vote.getVoteType().equals(voteType)).collect(Collectors.toList()).size();
		return totalVotesNo;
	}

	private void checkVotingSessionIsStillOpen(final VotingSession votingSessionFinded) {
		if (votingSessionFinded.isOpen()) {
			throw new VotingSessionIsStillOpenException();
		}
	}

}
