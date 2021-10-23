package br.com.sicred.voting.usecases;

import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Vote;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateVoteUseCase {
	
	public Vote create(final Vote voteToCreate) {
		log.info("voteToCreate: {}", voteToCreate);
		return null;
	}

}
