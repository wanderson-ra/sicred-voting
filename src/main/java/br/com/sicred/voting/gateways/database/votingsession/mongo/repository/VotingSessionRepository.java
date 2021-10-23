package br.com.sicred.voting.gateways.database.votingsession.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.sicred.voting.domains.VotingSession;

public interface VotingSessionRepository  extends MongoRepository<VotingSession, String> {	

}
