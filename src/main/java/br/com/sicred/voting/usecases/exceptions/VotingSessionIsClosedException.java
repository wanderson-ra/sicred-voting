package br.com.sicred.voting.usecases.exceptions;

import org.springframework.http.HttpStatus;

import br.com.sicred.voting.config.interceptors.controllers.SicredVotingBaseException;

public class VotingSessionIsClosedException extends SicredVotingBaseException {

	private static final long serialVersionUID = 1102458593547989525L;

	@Override
	public String getCode() {
		return "sicred.votingsession.error.closed";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}

	@Override
	public String getMessage() {
		return "Voting Session is closed.";
	}
}
