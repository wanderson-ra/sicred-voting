package br.com.sicred.voting.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Ruling;
import br.com.sicred.voting.gateways.database.ruling.RulingDatabaseGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreateRulingUseCase {
	
	
	@Autowired
	private RulingDatabaseGateway rulingDatabaseGateway;
	
	
	public Ruling create(final Ruling rulingToCreate) {
		log.info("rulingToCreate: {}", rulingToCreate);
		
		return this.rulingDatabaseGateway.create(rulingToCreate);

	}

}
