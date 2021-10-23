package br.com.sicred.voting.gateways.database.associate;

import br.com.sicred.voting.domains.Associate;

public interface AssociateDatebaseGateway {
	
	Associate create(final Associate associateToCreate);

}
