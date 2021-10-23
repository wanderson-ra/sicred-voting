package br.com.sicred.voting.usecases.exceptions;

import org.springframework.http.HttpStatus;

import br.com.sicred.voting.config.interceptors.controllers.SicredVotingBaseException;

public class AssociateAlreadyVotedInThisSessionException extends SicredVotingBaseException {

	private static final long serialVersionUID = -6247606292525918060L;

	@Override
	public String getCode() {
		return "sicred.vote.error.associateAlreadyVoted";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}

	@Override
	public String getMessage() {
		return "Associate  already voted in this session.";
	}

}
