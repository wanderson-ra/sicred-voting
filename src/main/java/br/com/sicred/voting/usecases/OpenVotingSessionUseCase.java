package br.com.sicred.voting.usecases;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.gateways.database.votingsession.VotingSessionDatabaseGateway;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OpenVotingSessionUseCase {

	@Autowired
	private VotingSessionDatabaseGateway votingSessionDatabaseGateway;
	

	public VotingSession open(final VotingSession votingSessionOpen) {
		log.info("votingSessionOpen: {}", votingSessionOpen);

		final LocalDateTime expiration = this.getExpiration(votingSessionOpen);
		
		final VotingSession votingSessionToOpen = this.remappingVotingSession(votingSessionOpen, expiration);

		return this.votingSessionDatabaseGateway.open(votingSessionToOpen);
	}

	private VotingSession remappingVotingSession(final VotingSession votingSessionOpen,
			final LocalDateTime expiration) {
		final VotingSession votingSessionToOpen = VotingSession.builder()
				.expiration(expiration)			
				.ruling(votingSessionOpen.getRuling()).build();
		return votingSessionToOpen;
	}

	private LocalDateTime getExpiration(final VotingSession votingSessionToOpen) {
		final LocalDateTime expiration = ObjectUtils.isEmpty(votingSessionToOpen.getExpiration())
				? LocalDateTime.now().plusMinutes(1)
				: votingSessionToOpen.getExpiration();
				
		return expiration;
	}

}
