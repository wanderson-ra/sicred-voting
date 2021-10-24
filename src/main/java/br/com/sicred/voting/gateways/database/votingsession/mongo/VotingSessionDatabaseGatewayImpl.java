package br.com.sicred.voting.gateways.database.votingsession.mongo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.gateways.database.votingsession.VotingSessionDatabaseGateway;
import br.com.sicred.voting.gateways.database.votingsession.mongo.repository.VotingSessionRepository;
import br.com.sicred.voting.gateways.exceptions.FindVotingSessionByIdDatabaseException;
import br.com.sicred.voting.gateways.exceptions.OpenVotingSessionDatabaseException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VotingSessionDatabaseGatewayImpl implements VotingSessionDatabaseGateway {

	@Autowired
	private VotingSessionRepository votingSessionRepository;

	public Optional<VotingSession> findById(final String id) {
		log.info("id: {}", id);

		try {
			return this.votingSessionRepository.findById(id);

		} catch (Exception error) {
			log.error("error: {}", error);
			throw new FindVotingSessionByIdDatabaseException();
		}
	}

	@Override
	public VotingSession open(final VotingSession votingSession) {
		log.info("votingSession: {}", votingSession);
		try {
			return this.votingSessionRepository.save(votingSession);

		} catch (Exception error) {
			log.error("error: {}", error);
			throw new OpenVotingSessionDatabaseException();
		}
	}

}
