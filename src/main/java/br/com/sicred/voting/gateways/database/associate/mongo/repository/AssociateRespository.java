package br.com.sicred.voting.gateways.database.associate.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.sicred.voting.domains.Associate;

public interface AssociateRespository extends MongoRepository<Associate, String> {
	
	Optional<Associate> findByCpf(final String cpf);

}
