package br.com.sicred.voting.gateways.database.vote.mongo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.gateways.database.vote.VoteDatabaseGateway;
import br.com.sicred.voting.gateways.database.vote.mongo.repository.VoteRepository;
import br.com.sicred.voting.gateways.exceptions.CreateVoteDatabaseException;
import br.com.sicred.voting.gateways.exceptions.FindAllVotesByVotingSessionDatabaseException;
import br.com.sicred.voting.gateways.exceptions.FindVoteBySessionIdAndAssciateIdAssociateDatabaseException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VoteDatabaseGatewayImpl implements VoteDatabaseGateway {

	@Autowired
	private VoteRepository voteRepository;

	@Override
	public Vote create(final Vote voteToCreate) {
		log.info("voteToCreate: {}", voteToCreate);

		try {
			return this.voteRepository.save(voteToCreate);
		} catch (Exception error) {
			log.error("error: {}", error);
			throw new CreateVoteDatabaseException();
		}

	}

	@Override
	public Optional<Vote> findByAssociateIdAndVotingSessionId(final String associateId, final String votingSessionId) {
		log.info("associateId: {}, votingSessionId: {}", associateId, votingSessionId);

		try {

			return this.voteRepository.findByAssociateIdAndVotingSessionId(associateId, votingSessionId);

		} catch (Exception error) {
			log.error("error: {}", error);
			throw new FindVoteBySessionIdAndAssciateIdAssociateDatabaseException();
		}

	}

	public List<Vote> findAllByVotingSessionId(String votingSessionId) {
		log.info("votingSessionId: {}", votingSessionId);
		try {

			return this.voteRepository.findAllByVotingSessionId(votingSessionId);

		} catch (Exception error) {
			log.error("error: {}", error);
			throw new FindAllVotesByVotingSessionDatabaseException();
		}
	}

}
