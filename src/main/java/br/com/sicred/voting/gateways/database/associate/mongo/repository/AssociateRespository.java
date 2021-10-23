package br.com.sicred.voting.gateways.database.associate.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.sicred.voting.domains.Associate;

public interface AssociateRespository extends MongoRepository<Associate, String> {

}
