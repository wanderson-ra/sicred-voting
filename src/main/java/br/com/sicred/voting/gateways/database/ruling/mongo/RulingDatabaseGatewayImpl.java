package br.com.sicred.voting.gateways.database.ruling.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sicred.voting.domains.Ruling;
import br.com.sicred.voting.gateways.database.ruling.RulingDatabaseGateway;
import br.com.sicred.voting.gateways.database.ruling.mongo.repository.RulingRepository;
import br.com.sicred.voting.gateways.exceptions.CreateRulingDatabaseException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RulingDatabaseGatewayImpl implements RulingDatabaseGateway {

	@Autowired
	private RulingRepository rulingRepository;
	
	

	public Ruling create(Ruling rulingToCreate) {
		log.info("rulingToCreate: {}", rulingToCreate);

		try {

			return this.rulingRepository.save(rulingToCreate);

		} catch (Exception error) {
			log.error("error: {}", error);
			throw new CreateRulingDatabaseException();			
		}

	}

}
