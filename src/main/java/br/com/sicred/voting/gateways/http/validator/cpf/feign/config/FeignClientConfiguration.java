package br.com.sicred.voting.gateways.http.validator.cpf.feign.config;

import feign.Logger;
import feign.codec.ErrorDecoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {	
	
	@Bean
	public ErrorDecoder errorDecoder() { return new FeignErrorDecoder();}

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
