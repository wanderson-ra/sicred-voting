package br.com.sicred.voting.gateways.database.associate;

import java.util.Optional;

import br.com.sicred.voting.domains.Associate;

public interface AssociateDatebaseGateway {
	
	Associate create(final Associate associateToCreate);
	Optional<Associate> findByCpf(final String cpf);
	Optional<Associate> findById(final String id);

}
