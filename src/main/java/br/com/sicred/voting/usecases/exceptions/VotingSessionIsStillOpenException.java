package br.com.sicred.voting.usecases.exceptions;

import org.springframework.http.HttpStatus;

import br.com.sicred.voting.config.interceptors.controllers.SicredVotingBaseException;

public class VotingSessionIsStillOpenException extends SicredVotingBaseException {

	private static final long serialVersionUID = 76498261849089574L;

	@Override
	public String getCode() {
		return "sicred.votingsession.error.isstillopen";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}

	@Override
	public String getMessage() {
		return "Voting Session is still open.";
	}

}
