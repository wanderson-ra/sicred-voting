package br.com.sicred.voting.gateways.http.validator.cpf.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sicred.voting.domains.enums.VoteCheck;
import br.com.sicred.voting.gateways.http.validator.cpf.CpfValidatorGateway;
import br.com.sicred.voting.gateways.http.validator.cpf.feign.client.CpfValidatorFeignClient;
import br.com.sicred.voting.gateways.http.validator.cpf.feign.client.json.ValidateCpfEnableToVoteResponseJson;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CpfValidatorGatewayImpl implements CpfValidatorGateway {

	final static String ABLE_TO_VOTE = "ABLE_TO_VOTE";
	final static String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";

	@Autowired
	private CpfValidatorFeignClient cpfValidatorFeignClient;

	public VoteCheck validateCpfToEnableToVote(final String cpf) {
		log.info("cpf: {}", cpf);

		final ValidateCpfEnableToVoteResponseJson response = this.cpfValidatorFeignClient
				.validateCpfEnableToVotefinal(cpf);

		if (response.getStatus().equals(ABLE_TO_VOTE)) {
			return VoteCheck.ENABLE;
		} else {
			return VoteCheck.DISABLE;
		}
	}

}
