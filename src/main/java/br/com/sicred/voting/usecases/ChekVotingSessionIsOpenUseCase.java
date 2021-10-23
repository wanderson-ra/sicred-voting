package br.com.sicred.voting.usecases;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.usecases.exceptions.VotingSessionIsClosedException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChekVotingSessionIsOpenUseCase {

	@Autowired
	private FindVotingSessionByIdUseCase findVotingSessionByIdUseCase;

	public void check(final String votingSessionId) {
		log.info("votingSessionId: {}", votingSessionId);

		final VotingSession votingSession = this.findVotingSessionByIdUseCase.find(votingSessionId);
		
		final LocalDateTime localDateTimeNow = LocalDateTime.now();
		
		if(votingSession.getExpiration().isBefore(localDateTimeNow)) {
			log.warn("voting session is closed: {}", votingSession);
			throw new VotingSessionIsClosedException();
		}		
	}

}
