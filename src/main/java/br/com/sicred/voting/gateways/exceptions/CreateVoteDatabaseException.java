package br.com.sicred.voting.gateways.exceptions;

import org.springframework.http.HttpStatus;

import br.com.sicred.voting.config.interceptors.controllers.SicredVotingBaseException;

public class CreateVoteDatabaseException extends SicredVotingBaseException {

	private static final long serialVersionUID = -3467779414838011548L;

	@Override
	public String getCode() {
		return "sicred.vote.database.error.create";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	@Override
	public String getMessage() {
		return "Error to create Vote.";
	}

}