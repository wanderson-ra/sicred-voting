package br.com.sicred.voting.gateways.database.votingsession;

import java.util.Optional;

import br.com.sicred.voting.domains.VotingSession;

public interface VotingSessionDatabaseGateway {

	Optional<VotingSession> findById(final String id);
	VotingSession open(final VotingSession votingSession);
}
