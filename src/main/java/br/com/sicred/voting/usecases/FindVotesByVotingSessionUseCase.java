package br.com.sicred.voting.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.gateways.database.vote.VoteDatabaseGateway;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FindVotesByVotingSessionUseCase {
	
	@Autowired
	private VoteDatabaseGateway voteDatabaseGateway;

	public List<Vote> find(final String votingSessionId) {
		log.info("votingSessionId: {}", votingSessionId);
		
		return this.voteDatabaseGateway.findAllByVotingSessionId(votingSessionId);
	}

}
