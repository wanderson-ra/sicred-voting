package br.com.sicred.voting.usecases.exceptions;

import org.springframework.http.HttpStatus;

import br.com.sicred.voting.config.interceptors.controllers.SicredVotingBaseException;

public class AssociateDisableToVoteException extends SicredVotingBaseException {

	private static final long serialVersionUID = 4106640816508363651L;

	@Override
	public String getCode() {
		return "sicred.associate.error.associatedisabledtovote";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}

	@Override
	public String getMessage() {
		return "Associate disabled to vote.";
	}
}
