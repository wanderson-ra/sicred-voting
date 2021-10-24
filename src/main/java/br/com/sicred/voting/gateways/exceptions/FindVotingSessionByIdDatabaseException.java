package br.com.sicred.voting.gateways.exceptions;

import org.springframework.http.HttpStatus;

import br.com.sicred.voting.config.interceptors.controllers.SicredVotingBaseException;

public class FindVotingSessionByIdDatabaseException extends SicredVotingBaseException {

	private static final long serialVersionUID = 3054556991197432725L;

	@Override
	public String getCode() {
		return "sicred.votingsession.database.error.findbyid";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	@Override
	public String getMessage() {
		return "Error to find by id.";
	}
}