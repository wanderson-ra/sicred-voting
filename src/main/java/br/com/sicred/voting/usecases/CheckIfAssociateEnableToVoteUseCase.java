package br.com.sicred.voting.usecases;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.domains.enums.VoteCheck;
import br.com.sicred.voting.gateways.http.validator.cpf.CpfValidatorGateway;
import br.com.sicred.voting.usecases.exceptions.AssociateDisableToVoteException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CheckIfAssociateEnableToVoteUseCase {
	
	@Autowired
	private FindAssociateByIdUseCase findAssociateByIdUseCase;
	
	@Autowired
	private CpfValidatorGateway cpfValidatorGateway;	
	
	
	public void check(final String id) {
		log.info("id: {}", id);
		
		final Associate  associateOptional = this.findAssociateByIdUseCase.find(id);		
		
		final VoteCheck voteCheck = this.cpfValidatorGateway.validateCpfToEnableToVote(associateOptional.getCpf());
		
		if(voteCheck.equals(VoteCheck.DISABLE)) {
			throw new AssociateDisableToVoteException();
		}
	}
}
