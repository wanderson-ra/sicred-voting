package br.com.sicred.voting.usecases.exceptions;

import org.springframework.http.HttpStatus;

import br.com.sicred.voting.config.interceptors.controllers.SicredVotingBaseException;

public class VotingSessionNoFoundExeption extends SicredVotingBaseException {	

	private static final long serialVersionUID = -4889334379462787919L;

	@Override
	public String getCode() {
		return "sicred.votingsession.error.notfound";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}

	@Override
	public String getMessage() {
		return "Voting Session not found.";
	}
}

