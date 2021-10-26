package br.com.sicred.voting.gateways.http.validator.cpf.feign.client;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.sicred.voting.gateways.http.validator.cpf.feign.client.json.ValidateCpfEnableToVoteResponseJson;

@FeignClient(name = "${feign.cpfValidator.name}", url = "${feign.cpfValidator.url}")
public interface CpfValidatorFeignClient {

	@GetMapping("users/{cpf}")
	ValidateCpfEnableToVoteResponseJson validateCpfEnableToVotefinal(@PathVariable(name = "cpf") @Valid String cpf);

}
