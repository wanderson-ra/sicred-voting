package br.com.sicred.voting.usecases;

import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Result;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetResultVotingSessionUseCase {

	public Result getResult(final String rulingId, final String votingSessionId) {
		log.info("rulingId: {}, votingSessionId: {}", rulingId, votingSessionId);
		
		return null;
	}

}
