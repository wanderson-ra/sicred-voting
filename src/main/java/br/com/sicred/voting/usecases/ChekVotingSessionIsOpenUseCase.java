package br.com.sicred.voting.usecases;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChekVotingSessionIsOpenUseCase {
	
	public void check(final String votingSessionId) {
		log.info("votingSessionId: {}", votingSessionId);
	}

}
