package br.com.sicred.voting.usecases;

import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.VotingSession;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OpenVotingSessionUseCase {
	
	public VotingSession open(final VotingSession votingSessionToOpen) {
		log.info("votingSessionToOpen: {}", votingSessionToOpen);
		return null;		
	}

}
