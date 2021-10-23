package br.com.sicred.voting.gateways.exceptions;

import org.springframework.http.HttpStatus;

import br.com.sicred.voting.config.interceptors.controllers.SicredVotingBaseException;

public class CreateAssociateDatabaseException extends SicredVotingBaseException {

	private static final long serialVersionUID = 8406603321967944173L;

	@Override
	public String getCode() {
		return "sicred.associate.database.error.create";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	@Override
	public String getMessage() {
		return "Error to create Associate.";
	}

}