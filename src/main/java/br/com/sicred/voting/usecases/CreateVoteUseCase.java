package br.com.sicred.voting.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.gateways.database.vote.VoteDatabaseGateway;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateVoteUseCase {

	@Autowired
	private VoteDatabaseGateway voteDatabaseGateway;

	public Vote create(final Vote voteToCreate) {
		log.info("voteToCreate: {}", voteToCreate);

		return this.voteDatabaseGateway.create(voteToCreate);

	}

}
