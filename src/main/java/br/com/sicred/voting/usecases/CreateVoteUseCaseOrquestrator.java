package br.com.sicred.voting.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Vote;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateVoteUseCaseOrquestrator {
	
	@Autowired
	private CheckVotingSessionIsOpenUseCase chekVotingSessionIsOpenUseCase;	
	
	@Autowired
	private CheckAssociateAlreadyVotedUseCase checkAssociateAlreadyVotedUseCase;	
	
	@Autowired
	private CreateVoteUseCase createVoteUseCase;
	

	public Vote create(final Vote voteToCreate) {
		log.info("voteToCreate: {}", voteToCreate);
		
		this.chekVotingSessionIsOpenUseCase.check(voteToCreate.getVotingSession().getId());
		
		this.checkAssociateAlreadyVotedUseCase.check(voteToCreate.getAssociate().getId(), voteToCreate.getVotingSession().getId());
		
		return this.createVoteUseCase.create(voteToCreate);
	}

}
