package br.com.sicred.voting.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import br.com.sicred.voting.domains.Ruling;
import br.com.sicred.voting.gateways.database.ruling.RulingDatabaseGateway;
import br.com.sicred.voting.utils.BaseTest;

public class CreateRulingUseCaseUniTest extends BaseTest{
	
	@InjectMocks
	private CreateRulingUseCase createRulingUseCase;
	
	@Mock
	private RulingDatabaseGateway rulingDatabaseGateway;
	
	@Test
	@DisplayName("should create ruling with success")
	public void shoulBySuccess() {
	
		final Ruling rulingToCreated = this.domainsDatabuilder.getRulingDataBuilder().toCreate().build();
		
		final Ruling rulingCreated = this.domainsDatabuilder.getRulingDataBuilder().build();

		when(this.createRulingUseCase.create(any(Ruling.class))).thenReturn(rulingCreated);

		final Ruling response = this.createRulingUseCase.create(rulingToCreated);

		final ArgumentCaptor<Ruling> rulingCaptor = ArgumentCaptor.forClass(Ruling.class);

		verify(this.rulingDatabaseGateway, VerificationModeFactory.times(1)).create(rulingCaptor.capture());

		final Ruling rulingCaptured = rulingCaptor.getValue();		
		
		this.assertRulingCaptured(rulingToCreated, rulingCaptured);
		
		this.assertRulingResponse(rulingCreated, response);

	}

	private void assertRulingResponse(final Ruling rulingCreated, final Ruling response) {
		assertEquals(rulingCreated.getName(), response.getName());
		assertEquals(rulingCreated.getDescription(), response.getDescription());
		assertEquals(rulingCreated.getId(), response.getId());
		assertEquals(rulingCreated.getLastUpdate(), response.getLastUpdate());
		assertEquals(rulingCreated.getCreatedAt(), response.getCreatedAt());
	}

	private void assertRulingCaptured(final Ruling rulingToCreated, final Ruling rulingCaptured) {
		assertEquals(rulingToCreated.getName(), rulingCaptured.getName());
		assertEquals(rulingToCreated.getDescription(), rulingCaptured.getDescription());
		assertEquals(null, rulingCaptured.getId());
		assertEquals(null, rulingCaptured.getLastUpdate());
		assertEquals(null, rulingCaptured.getCreatedAt());
	}

}
