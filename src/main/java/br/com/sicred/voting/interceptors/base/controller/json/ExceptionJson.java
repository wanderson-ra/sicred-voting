package br.com.sicred.voting.interceptors.base.controller.json;

import br.com.sicred.voting.interceptors.base.SicredVotingBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ExceptionJson {

	private final String code;
	private final String message;
	
	public ExceptionJson(final SicredVotingBaseException baseException) {
		this.code = baseException.getCode();
		this.message = baseException.getMessage();
	}
}
