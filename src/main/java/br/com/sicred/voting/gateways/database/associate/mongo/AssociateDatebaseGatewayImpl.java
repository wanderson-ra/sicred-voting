package br.com.sicred.voting.gateways.database.associate.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.gateways.database.associate.AssociateDatebaseGateway;
import br.com.sicred.voting.gateways.database.associate.mongo.repository.AssociateRespository;
import br.com.sicred.voting.gateways.exceptions.CreateAssociateDatabaseException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AssociateDatebaseGatewayImpl implements AssociateDatebaseGateway {

	@Autowired
	private AssociateRespository associateRespository;

	public Associate create(Associate associateToCreate) {
		log.info("associateToCreate: {}", associateToCreate);

		try {

			return this.associateRespository.save(associateToCreate);

		} catch (Exception error) {
			log.error("error: {}", error);
			throw new CreateAssociateDatabaseException();

		}

	}

}
