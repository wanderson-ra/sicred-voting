package br.com.sicred.voting.usecases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.gateways.database.associate.AssociateDatebaseGateway;
import br.com.sicred.voting.usecases.exceptions.AssociateAlreadyExistsWithCpfException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateAssociateUseCase {

	@Autowired
	private AssociateDatebaseGateway associateDatebaseGateway;

	@Autowired
	private FindAssociateByCpfUseCase findAssociateByCpfUseCase;

	public Associate create(final Associate associateToCreate) {
		log.info("associateToCreate: {}", associateToCreate);

		final Optional<Associate> associateOptional = this.findAssociateByCpfUseCase.find(associateToCreate.getCpf());

		this.checkAssociateAlreadyExists(associateOptional);

		return this.associateDatebaseGateway.create(associateToCreate);
	}

	private void checkAssociateAlreadyExists(final Optional<Associate> associateOptional) {
		if (associateOptional.isPresent()) {
			throw new AssociateAlreadyExistsWithCpfException();
		}
	}

}
