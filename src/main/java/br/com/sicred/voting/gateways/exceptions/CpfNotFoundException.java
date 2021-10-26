package br.com.sicred.voting.gateways.exceptions;

import org.springframework.http.HttpStatus;

import br.com.sicred.voting.config.interceptors.controllers.SicredVotingBaseException;

public class CpfNotFoundException extends SicredVotingBaseException {

	private static final long serialVersionUID = -2962297553064681L;

	@Override
	public String getCode() {
		return "sicred.associate.validator.error.cpfnotfound";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	@Override
	public String getMessage() {
		return "Cpf not found";
	}

}