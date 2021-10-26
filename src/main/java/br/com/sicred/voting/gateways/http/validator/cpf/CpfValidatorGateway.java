package br.com.sicred.voting.gateways.http.validator.cpf;

import br.com.sicred.voting.domains.enums.VoteCheck;

public interface CpfValidatorGateway {

	VoteCheck validateCpfToEnableToVote(final String cpf);
}
