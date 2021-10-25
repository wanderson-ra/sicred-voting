package br.com.sicred.voting.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.gateways.database.associate.AssociateDatebaseGateway;
import br.com.sicred.voting.utils.BaseTest;

public class FindAssociateByIdUseCaseUniTest extends BaseTest {
	
	@InjectMocks
	private FindAssociateByIdUseCase findAssociateByIdUseCase;
	
	@Mock
	private AssociateDatebaseGateway associateDatebaseGateway;
	
	@Test
	@DisplayName("Should by find by id with success")
	public void shouldFindByIdSuccess() {
		
		final Associate associateFinded = this.domainsDatabuilder.getAssociateDataBuilder().build();
		
		final String id = this.faker.internet().uuid();
		
		when(this.associateDatebaseGateway.findById(id)).thenReturn(Optional.of(associateFinded));
		
		final Optional<Associate> associateFindedResponse = this.findAssociateByIdUseCase.find(id); 
		
		assertEquals(associateFinded.getCpf(), associateFindedResponse.get().getCpf());
		assertEquals(associateFinded.getCreatedAt(), associateFindedResponse.get().getCreatedAt());
		assertEquals(associateFinded.getId(), associateFindedResponse.get().getId());
		assertEquals(associateFinded.getLastUpdate(), associateFindedResponse.get().getLastUpdate());
		assertEquals(associateFinded.getName(), associateFindedResponse.get().getName());
	}

}
