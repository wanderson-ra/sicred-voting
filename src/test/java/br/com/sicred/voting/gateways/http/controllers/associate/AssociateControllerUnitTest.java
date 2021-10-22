package br.com.sicred.voting.gateways.http.controllers.associate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.gateways.http.controllers.associate.json.CreateAssociateRequestJson;
import br.com.sicred.voting.gateways.http.controllers.associate.json.CreateAssociateResponseJson;
import br.com.sicred.voting.usecases.CreateAssociateUseCase;
import br.com.sicred.voting.utils.BaseTest;

public class AssociateControllerUnitTest extends BaseTest {
	
	@InjectMocks
	private AssociateController associateController;
	
	
	@Mock
	private CreateAssociateUseCase createAssociateUseCase;
	
	@Test
	@DisplayName("shoul create associate success")
	public void shouldBySuccess() {
		
		final CreateAssociateRequestJson createAssociateRequestJson = CreateAssociateRequestJson.builder()
				.name(this.faker.name().fullName())
				.cpf(this.faker.internet().uuid())
				.build();
		
		final Associate associateCreated = this.domainsDatabuilder.getAssociateDataBuilder().build();
		
		when(this.createAssociateUseCase.create(any(Associate.class))).thenReturn(associateCreated);
		
		
		final CreateAssociateResponseJson response = this.associateController.create(createAssociateRequestJson);
		
		final ArgumentCaptor<Associate> associateCaptor = ArgumentCaptor.forClass(Associate.class);
		
		verify(this.createAssociateUseCase, VerificationModeFactory.times(1)).create(associateCaptor.capture());
		
		final Associate associateCaptured = associateCaptor.getValue();
		
		
		assertEquals(associateCreated.getId(), response.getId());
		
		
		assertEquals(createAssociateRequestJson.getCpf(), associateCaptured.getCpf());
		assertEquals(createAssociateRequestJson.getName(), associateCaptured.getName());
		assertEquals(null, associateCaptured.getId());
		assertEquals(null, associateCaptured.getCreatedAt());
		assertEquals(null, associateCaptured.getLastUpdate());
	}

}
