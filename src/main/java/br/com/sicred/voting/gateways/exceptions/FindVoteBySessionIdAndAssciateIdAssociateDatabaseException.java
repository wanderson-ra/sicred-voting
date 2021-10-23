package br.com.sicred.voting.gateways.exceptions;

import org.springframework.http.HttpStatus;

import br.com.sicred.voting.config.interceptors.controllers.SicredVotingBaseException;

public class FindVoteBySessionIdAndAssciateIdAssociateDatabaseException extends SicredVotingBaseException {

	private static final long serialVersionUID = 1799857211759402208L;

	@Override
	public String getCode() {
		return "sicred.vote.database.error.findbysessionid";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	@Override
	public String getMessage() {
		return "Error to find Vote.";
	}

}