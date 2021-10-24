package br.com.sicred.voting.usecases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.gateways.database.vote.VoteDatabaseGateway;
import br.com.sicred.voting.usecases.exceptions.AssociateAlreadyVotedInThisSessionException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CheckAssociateAlreadyVotedUseCase {

	@Autowired
	private VoteDatabaseGateway voteDatabaseGateway;

	public void check(final String associateId, final String votingSessionId) {
		log.info("votingSessionId: {}, associateId: {}", votingSessionId, associateId);

		final Optional<Vote> optionalVote = this.voteDatabaseGateway.findByAssociateIdAndVotingSessionId(associateId,
				votingSessionId);

		if (optionalVote.isPresent()) {
			log.warn("associate already voted in this session associateId: {}, votingSessionId:{}", associateId,
					votingSessionId);
			throw new AssociateAlreadyVotedInThisSessionException();
		}
	}

}
