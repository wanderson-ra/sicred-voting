package br.com.sicred.voting.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Vote;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FindVotesByVotingSessionUseCase {

	public List<Vote> find(final String votingSessionId) {
		log.info("votingSessionId: {}", votingSessionId);
		return null;
	}

}
