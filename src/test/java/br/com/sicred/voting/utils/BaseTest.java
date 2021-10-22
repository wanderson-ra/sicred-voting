package br.com.sicred.voting.utils;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import com.github.javafaker.Faker;

import br.com.sicred.voting.databuilders.domains.DomainsDatabuilder;

@ActiveProfiles("test")
public class BaseTest {

	protected DomainsDatabuilder domainsDatabuilder;
	
	protected Faker faker;

	@BeforeEach
	public void init() {
		this.domainsDatabuilder = new DomainsDatabuilder();		
		this.faker = new Faker();
		MockitoAnnotations.openMocks(this);
	}
}
