package br.com.sicred.voting.usecases;

import org.springframework.stereotype.Service;

import br.com.sicred.voting.domains.Ruling;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreateRulingUseCase {

	public Ruling create(final Ruling rulingToCreate) {
		log.info("rulingToCreate: {}", rulingToCreate);
		return null;

	}

}
