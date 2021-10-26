package br.com.sicred.voting.usecases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.gateways.database.associate.AssociateDatebaseGateway;
import br.com.sicred.voting.usecases.exceptions.AssociateNoFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FindAssociateByIdUseCase {

	@Autowired
	private AssociateDatebaseGateway associateDatebaseGateway;

	public Associate find(final String id) {
		log.info("id: {}", id);

		final Optional<Associate> associateOptional = this.associateDatebaseGateway.findById(id);

		if (!associateOptional.isPresent()) {
			throw new AssociateNoFoundException();
		}

		return associateOptional.get();
	}

}
