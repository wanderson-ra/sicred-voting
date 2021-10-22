package br.com.sicred.voting.usecases;

import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Associate;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateAssociateUseCase {
	
	public Associate create(final Associate associateToCreate) {
		log.info("associateToCreate: {}", associateToCreate);
		return null;
	}

}
