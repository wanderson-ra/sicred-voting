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
		
		final Associate  associateFinded = this.findAssociateByIdUseCase.find(id);		
		
		final VoteCheck voteCheck = this.cpfValidatorGateway.validateCpfToEnableToVote(associateFinded.getCpf());
		
		if(voteCheck.equals(VoteCheck.DISABLE)) {
			log.warn("associate disabled to vote:{}", associateFinded);
			throw new AssociateDisableToVoteException();
		}
	}
}
