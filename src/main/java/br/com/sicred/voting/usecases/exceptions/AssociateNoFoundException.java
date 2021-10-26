package br.com.sicred.voting.usecases.exceptions;

import org.springframework.http.HttpStatus;

import br.com.sicred.voting.config.interceptors.controllers.SicredVotingBaseException;

public class AssociateNoFoundException extends SicredVotingBaseException {

	private static final long serialVersionUID = 2228570893263453899L;

	@Override
	public String getCode() {
		return "sicred.associate.error.associatenotfound";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}

	@Override
	public String getMessage() {
		return "Associate not found.";
	}
}
