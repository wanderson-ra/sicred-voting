package br.com.sicred.voting.gateways.database.vote;

import java.util.List;
import java.util.Optional;

import br.com.sicred.voting.domains.Vote;

public interface VoteDatabaseGateway {
	
	Vote create(final Vote voteToCreate);	
	Optional<Vote> findByAssociateIdAndVotingSessionId(final String votingSessionId, final String associateId);
	List<Vote> findAllByVotingSessionId(final String votingSessionId);
}
