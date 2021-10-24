package br.com.sicred.voting.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.gateways.database.associate.AssociateDatebaseGateway;
import br.com.sicred.voting.usecases.exceptions.AssociateAlreadyExistsWithCpfException;
import br.com.sicred.voting.utils.BaseTest;

public class CreateAssociateUseCaseUnitTest extends BaseTest {

	@InjectMocks
	private CreateAssociateUseCase createAssociateUseCase;

	@Mock
	private AssociateDatebaseGateway associateDatebaseGateway;

	@Mock
	private FindAssociateByCpfUseCase findAssociateByCpfUseCase;

	@Test
	@DisplayName("Should by create associate with success")
	public void shouldBySuccess() {

		final Associate associateToCreate = this.domainsDatabuilder.getAssociateDataBuilder().toCreate().build();

		final Associate associateCreated = this.domainsDatabuilder.getAssociateDataBuilder().build();

		when(this.associateDatebaseGateway.create(any(Associate.class))).thenReturn(associateCreated);

		when(this.findAssociateByCpfUseCase.find(associateToCreate.getCpf())).thenReturn(Optional.empty());

		final Associate response = this.createAssociateUseCase.create(associateToCreate);

		final ArgumentCaptor<Associate> associateCaptor = ArgumentCaptor.forClass(Associate.class);

		verify(this.associateDatebaseGateway, VerificationModeFactory.times(1)).create(associateCaptor.capture());

		final Associate associateCaptured = associateCaptor.getValue();

		this.assertResponse(associateCreated, response);

		this.assertCaptured(associateToCreate, associateCaptured);
	}

	@Test
	@DisplayName("Should by create associate with error associate already exists")
	public void shouldByErrorAssociateAlreadyExists() {

		final Associate associateFinded = this.domainsDatabuilder.getAssociateDataBuilder().build();

		final Associate associateToCreate = this.domainsDatabuilder.getAssociateDataBuilder().toCreate().build();

		when(this.findAssociateByCpfUseCase.find(associateToCreate.getCpf())).thenReturn(Optional.of(associateFinded));

		final AssociateAlreadyExistsWithCpfException error = assertThrows(
				AssociateAlreadyExistsWithCpfException.class, () -> {
					this.createAssociateUseCase.create(associateToCreate);
				});

		assertEquals(error.getCode(), "sicred.associate.error.associateAlreadyExistWithCpf");
		assertEquals(error.getMessage(), "Associate  already exists with cpf.");

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
