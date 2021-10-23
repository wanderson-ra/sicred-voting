package br.com.sicred.voting.usecases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.gateways.database.votingsession.VotingSessionDatabaseGateway;
import br.com.sicred.voting.usecases.exceptions.VotingSessionIsClosedException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FindVotingSessionByIdUseCase {

	@Autowired
	private VotingSessionDatabaseGateway votingSessionDatabaseGateway;

	public VotingSession find(final String id) {
		log.info("id: {}", id);

		final Optional<VotingSession> optionalVotingSession = this.votingSessionDatabaseGateway.findById(id);

		if (!optionalVotingSession.isPresent()) {
			log.warn("voting session not found: {}", id);
			throw new VotingSessionIsClosedException();
		}

		return optionalVotingSession.get();
	}

}
