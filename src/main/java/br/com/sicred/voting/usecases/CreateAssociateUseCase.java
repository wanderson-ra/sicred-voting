package br.com.sicred.voting.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.gateways.database.associate.AssociateDatebaseGateway;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateAssociateUseCase {
	
	@Autowired
	private AssociateDatebaseGateway associateDatebaseGateway;
	
	
	public Associate create(final Associate associateToCreate) {
		log.info("associateToCreate: {}", associateToCreate);
		
		return this.associateDatebaseGateway.create(associateToCreate);
	}

}
