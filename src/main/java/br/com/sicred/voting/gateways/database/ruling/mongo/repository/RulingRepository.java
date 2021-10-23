package br.com.sicred.voting.gateways.database.ruling.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.sicred.voting.domains.Ruling;


public interface RulingRepository extends MongoRepository<Ruling, String> {

}
