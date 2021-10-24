package br.com.sicred.voting.usecases.exceptions;

import org.springframework.http.HttpStatus;

import br.com.sicred.voting.config.interceptors.controllers.SicredVotingBaseException;

public class AssociateAlreadyExistsWithCpfException extends SicredVotingBaseException {

	private static final long serialVersionUID = 3436301927072812135L;

	@Override
	public String getCode() {
		return "sicred.associate.error.associateAlreadyExistWithCpf";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}

	@Override
	public String getMessage() {
		return "Associate  already exists with cpf.";
	}
}
