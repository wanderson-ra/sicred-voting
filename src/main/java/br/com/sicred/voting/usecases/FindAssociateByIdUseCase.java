package br.com.sicred.voting.usecases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.gateways.database.associate.AssociateDatebaseGateway;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FindAssociateByIdUseCase {

	@Autowired
	private AssociateDatebaseGateway associateDatebaseGateway;

	public Optional<Associate> find(final String id) {
		log.info("id: {}", id);

		return this.associateDatebaseGateway.findById(id);
	}

}
