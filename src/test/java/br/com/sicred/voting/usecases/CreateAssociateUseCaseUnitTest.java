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

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.gateways.database.associate.AssociateDatebaseGateway;
import br.com.sicred.voting.utils.BaseTest;

public class CreateAssociateUseCaseUnitTest extends BaseTest {
	
	@InjectMocks
	private CreateAssociateUseCase createAssociateUseCase;
	
	@Mock
	private AssociateDatebaseGateway associateDatebaseGateway;
	
	@Test
	@DisplayName("shoul create associate success")
	public void shouldBySuccess() {
		
		final Associate associateToCreate = this.domainsDatabuilder.getAssociateDataBuilder().toCreate().build();
		
		final Associate associateCreated = this.domainsDatabuilder.getAssociateDataBuilder().build();
		
		when(this.associateDatebaseGateway.create(any(Associate.class))).thenReturn(associateCreated);
		
		
		final Associate response = this.createAssociateUseCase.create(associateToCreate);
		
		final ArgumentCaptor<Associate> associateCaptor = ArgumentCaptor.forClass(Associate.class);
		
		verify(this.associateDatebaseGateway, VerificationModeFactory.times(1)).create(associateCaptor.capture());
		
		final Associate associateCaptured = associateCaptor.getValue();
		
		
		this.assertResponse(associateCreated, response);		
		
		this.assertCaptured(associateToCreate, associateCaptured);
	}

	private void assertCaptured(final Associate associateToCreate, final Associate associateCaptured) {
		assertEquals(associateToCreate.getCpf(), associateCaptured.getCpf());
		assertEquals(associateToCreate.getName(), associateCaptured.getName());
		assertEquals(null, associateCaptured.getId());
		assertEquals(null, associateCaptured.getCreatedAt());
		assertEquals(null, associateCaptured.getLastUpdate());
	}

	private void assertResponse(final Associate associateCreated, final Associate response) {
		assertEquals(associateCreated.getId(), response.getId());
		assertEquals(associateCreated.getCpf(), response.getCpf());
		assertEquals(associateCreated.getName(), response.getName());
		assertEquals(associateCreated.getCreatedAt(), response.getCreatedAt());
		assertEquals(associateCreated.getLastUpdate(), response.getLastUpdate());
	}

}
