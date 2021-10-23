package br.com.sicred.voting.gateways.database.associate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.gateways.database.associate.mongo.AssociateDatebaseGatewayImpl;
import br.com.sicred.voting.gateways.database.associate.mongo.repository.AssociateRespository;
import br.com.sicred.voting.gateways.exceptions.CreateAssociateDatabaseException;
import br.com.sicred.voting.utils.BaseTest;

public class AssociateDatebaseGatewayImplUnitTest extends BaseTest {

	@InjectMocks
	private AssociateDatebaseGatewayImpl associateDatebaseGatewayImpl;

	@Mock
	private AssociateRespository associateRespository;
	
	

	@Test
	@DisplayName("shoul create associate success")
	public void shoulBySuccess() {
		final Associate associateToCreate = this.domainsDatabuilder.getAssociateDataBuilder().toCreate().build();

		final Associate associateCreated = this.domainsDatabuilder.getAssociateDataBuilder().build();

		when(this.associateRespository.save(any(Associate.class))).thenReturn(associateCreated);

		final Associate response = this.associateDatebaseGatewayImpl.create(associateToCreate);

		final ArgumentCaptor<Associate> associateCaptor = ArgumentCaptor.forClass(Associate.class);

		verify(this.associateRespository, VerificationModeFactory.times(1)).save(associateCaptor.capture());

		final Associate associateCaptured = associateCaptor.getValue();

		this.assertResponse(associateCreated, response);

		this.assertCaptured(associateToCreate, associateCaptured);
	}

	@Test
	@DisplayName("shoul create associate error database")
	public void shoulByErrorDatabase() {
		final Associate associateToCreate = this.domainsDatabuilder.getAssociateDataBuilder().toCreate().build();

		doThrow(new RuntimeException()).when(this.associateRespository).save(associateToCreate);

		final CreateAssociateDatabaseException error = assertThrows(CreateAssociateDatabaseException.class, () -> {
			this.associateDatebaseGatewayImpl.create(associateToCreate);
		});

		assertEquals(error.getCode(), "sicred.associate.database.error.create");
		assertEquals(error.getMessage(), "Error to create Associate.");

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
