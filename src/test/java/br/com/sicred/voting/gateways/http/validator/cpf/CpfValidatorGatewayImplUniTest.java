package br.com.sicred.voting.gateways.http.validator.cpf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.sicred.voting.domains.enums.VoteCheck;
import br.com.sicred.voting.gateways.http.validator.cpf.feign.CpfValidatorGatewayImpl;
import br.com.sicred.voting.gateways.http.validator.cpf.feign.client.CpfValidatorFeignClient;
import br.com.sicred.voting.gateways.http.validator.cpf.feign.client.json.ValidateCpfEnableToVoteResponseJson;
import br.com.sicred.voting.utils.BaseTest;

public class CpfValidatorGatewayImplUniTest extends BaseTest {

	@InjectMocks
	private CpfValidatorGatewayImpl cpfValidatorGatewayImpl;

	@Mock
	private CpfValidatorFeignClient cpfValidatorFeignClient;

	@Test
	@DisplayName("Should by able to vote")
	public void shouldByValidateCpfToEnableToVoteAble() {
		final String cpf = this.faker.internet().uuid();

		when(this.cpfValidatorFeignClient.validateCpfEnableToVotefinal(cpf))
				.thenReturn(ValidateCpfEnableToVoteResponseJson.builder().status("ABLE_TO_VOTE").build());
		
		final VoteCheck voteCheck = this.cpfValidatorGatewayImpl.validateCpfToEnableToVote(cpf);
		
		assertEquals(VoteCheck.ENABLE, voteCheck);
	}
	
	@Test
	@DisplayName("Should by unable to vote")
	public void shouldByValidateCpfToEnableToVoteUnable() {
		final String cpf = this.faker.internet().uuid();

		when(this.cpfValidatorFeignClient.validateCpfEnableToVotefinal(cpf))
				.thenReturn(ValidateCpfEnableToVoteResponseJson.builder().status("UNABLE_TO_VOTE").build());
		
		final VoteCheck voteCheck = this.cpfValidatorGatewayImpl.validateCpfToEnableToVote(cpf);
		
		assertEquals(VoteCheck.DISABLE, voteCheck);
	}

}
