package br.com.sicred.voting.gateways.http.controllers.ruling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import br.com.sicred.voting.domains.Ruling;
import br.com.sicred.voting.gateways.http.controllers.ruling.json.CreateRulingRequestJson;
import br.com.sicred.voting.gateways.http.controllers.ruling.json.CreateRulingResponseJson;
import br.com.sicred.voting.usecases.CreateRulingUseCase;
import br.com.sicred.voting.utils.BaseTest;

public class RulingControllerUniTest extends BaseTest {

	@InjectMocks
	private RulingController rulingController;

	@Mock
	private CreateRulingUseCase createRulingUseCase;

	@Test
	@DisplayName("shoul create ruling success")
	public void shoulBySuccess() {

		final CreateRulingRequestJson createRulingRequestJson = new CreateRulingRequestJson(faker.company().name(),
				this.faker.lorem().paragraph());

		final Ruling rulingCreated = this.domainsDatabuilder.getRulingDataBuilder().build();

		when(this.createRulingUseCase.create(any(Ruling.class))).thenReturn(rulingCreated);

		final CreateRulingResponseJson response = this.rulingController.create(createRulingRequestJson);

		final ArgumentCaptor<Ruling> rulingCaptor = ArgumentCaptor.forClass(Ruling.class);

		verify(this.createRulingUseCase, VerificationModeFactory.times(1)).create(rulingCaptor.capture());

		final Ruling rulingCaptured = rulingCaptor.getValue();

		assertEquals(rulingCreated.getId(), response.getId());
		
		assertEquals(createRulingRequestJson.getName(), rulingCaptured.getName());
		assertEquals(createRulingRequestJson.getDescription(), rulingCaptured.getDescription());
		assertEquals(null, rulingCaptured.getId());
		assertEquals(null, rulingCaptured.getLastUpdate());
		assertEquals(null, rulingCaptured.getCreatedAt());

	}

}
