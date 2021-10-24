package br.com.sicred.voting.usecases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.gateways.database.associate.AssociateDatebaseGateway;
import br.com.sicred.voting.utils.BaseTest;

public class FindAssociateByCpfUseCaseUniTest extends BaseTest{
	
	@InjectMocks
	private FindAssociateByCpfUseCase findAssociateByCpfUseCase;
	
	@Mock
	private AssociateDatebaseGateway associateDatebaseGateway;
	
	
	@Test
	@DisplayName("Should by find by cpf with success")
	public void shouldFindByCpfSuccess() {
		
		final Associate associateFinded = this.domainsDatabuilder.getAssociateDataBuilder().build();
		
		final String cpf = this.faker.internet().uuid();
		
		when(this.associateDatebaseGateway.findByCpf(cpf)).thenReturn(Optional.of(associateFinded));
		
		final Optional<Associate> associateFindedResponse = this.findAssociateByCpfUseCase.find(cpf); 
		
		assertEquals(associateFinded.getCpf(), associateFindedResponse.get().getCpf());
		assertEquals(associateFinded.getCreatedAt(), associateFindedResponse.get().getCreatedAt());
		assertEquals(associateFinded.getId(), associateFindedResponse.get().getId());
		assertEquals(associateFinded.getLastUpdate(), associateFindedResponse.get().getLastUpdate());
		assertEquals(associateFinded.getName(), associateFindedResponse.get().getName());
	}

}
