package br.com.sicred.voting.gateways.exceptions;

import org.springframework.http.HttpStatus;

import br.com.sicred.voting.config.interceptors.controllers.SicredVotingBaseException;

public class FindAllVotesByVotingSessionDatabaseException extends SicredVotingBaseException {

	private static final long serialVersionUID = 2814727376298172634L;

	@Override
	public String getCode() {
		return "sicred.vote.database.error.findallbyvotingsession";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	@Override
	public String getMessage() {
		return "Error to find all by voting session.";
	}

}