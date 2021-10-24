package br.com.sicred.voting.gateways.database.vote.mongo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.sicred.voting.domains.Vote;

public interface VoteRepository extends MongoRepository<Vote, String> {

	Optional<Vote> findByAssociateIdAndVotingSessionId(final String associateId, final String votingSessionId);
	List<Vote> findAllByVotingSessionId(final String votingSessionId);
}
