package br.com.sicred.voting.gateways.database.associate.mongo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.gateways.database.associate.mongo.repository.AssociateRespository;
import br.com.sicred.voting.gateways.exceptions.CreateAssociateDatabaseException;
import br.com.sicred.voting.gateways.exceptions.FindAssociateByCpfDatabaseException;
import br.com.sicred.voting.utils.BaseTest;

public class AssociateDatebaseGatewayImplUnitTest extends BaseTest {

	@InjectMocks
	private AssociateDatebaseGatewayImpl associateDatebaseGatewayImpl;

	@Mock
	private AssociateRespository associateRespository;

	@Test
	@DisplayName("Should by create associate success")
	public void shouldBySuccess() {
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
	@DisplayName("Should by create associate error database")
	public void shouldByErrorDatabase() {
		final Associate associateToCreate = this.domainsDatabuilder.getAssociateDataBuilder().toCreate().build();

		doThrow(new RuntimeException()).when(this.associateRespository).save(associateToCreate);

		final CreateAssociateDatabaseException error = assertThrows(CreateAssociateDatabaseException.class, () -> {
			this.associateDatebaseGatewayImpl.create(associateToCreate);
		});

		assertEquals(error.getCode(), "sicred.associate.database.error.create");
		assertEquals(error.getMessage(), "Error to create Associate.");

	}

	@Test
	@DisplayName("Should by find by cpf success")
	public void shouldByCpfWithSuccess() {

		final Associate associateFinded = this.domainsDatabuilder.getAssociateDataBuilder().build();

		final String cpf = this.faker.internet().uuid();

		when(this.associateRespository.findByCpf(cpf)).thenReturn(Optional.of(associateFinded));

		final Optional<Associate> associateFindedResponse = this.associateDatebaseGatewayImpl.findByCpf(cpf);

		assertEquals(associateFinded.getCpf(), associateFindedResponse.get().getCpf());
		assertEquals(associateFinded.getCreatedAt(), associateFindedResponse.get().getCreatedAt());
		assertEquals(associateFinded.getId(), associateFindedResponse.get().getId());
		assertEquals(associateFinded.getLastUpdate(), associateFindedResponse.get().getLastUpdate());
		assertEquals(associateFinded.getName(), associateFindedResponse.get().getName());
	}

	@Test
	@DisplayName("Should by find by cpf error database")
	public void shouldByCpfWithErrorDatabase() {

		final String cpf = this.faker.internet().uuid();

		doThrow(new RuntimeException()).when(this.associateRespository).findByCpf(cpf);
		
		final FindAssociateByCpfDatabaseException error = assertThrows(FindAssociateByCpfDatabaseException.class, () -> {
			this.associateDatebaseGatewayImpl.findByCpf(cpf);
		});

		assertEquals(error.getCode(), "sicred.associate.database.error.findbycpf");
		assertEquals(error.getMessage(), "Error to find associate by cpf.");
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
