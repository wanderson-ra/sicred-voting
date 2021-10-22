package br.com.sicred.voting.gateways.database.ruling;

import br.com.sicred.voting.domains.Ruling;

public interface RulingDatabaseGateway {
	
	Ruling create(final Ruling rulingToCreate);

}
