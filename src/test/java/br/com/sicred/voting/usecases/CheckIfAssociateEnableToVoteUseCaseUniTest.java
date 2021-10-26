package br.com.sicred.voting.usecases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.domains.enums.VoteCheck;
import br.com.sicred.voting.gateways.http.validator.cpf.CpfValidatorGateway;
import br.com.sicred.voting.usecases.exceptions.AssociateDisableToVoteException;
import br.com.sicred.voting.utils.BaseTest;

public class CheckIfAssociateEnableToVoteUseCaseUniTest extends BaseTest {

	@InjectMocks
	private CheckIfAssociateEnableToVoteUseCase checkIfAssociateEnableToVoteUseCase;

	@Mock
	private FindAssociateByIdUseCase findAssociateByIdUseCase;

	@Mock
	private CpfValidatorGateway cpfValidatorGateway;

	@Test
	@DisplayName("Should by associate enabled to vote")
	public void shouldByAssociateEnabledToVote() {

		final VoteCheck voteCheck = VoteCheck.ENABLE;

		final String associateId = faker.internet().uuid();

		final Associate associate = this.domainsDatabuilder.getAssociateDataBuilder().build();

		when(this.findAssociateByIdUseCase.find(associateId)).thenReturn(associate);

		when(this.cpfValidatorGateway.validateCpfToEnableToVote(associate.getCpf())).thenReturn(voteCheck);

		assertDoesNotThrow(() -> this.checkIfAssociateEnableToVoteUseCase.check(associateId));
	}

	@Test
	@DisplayName("Should by associate disabled to vote")
	public void shouldByAssociateDisabledToVote() {

		final VoteCheck voteCheck = VoteCheck.DISABLE;

		final String associateId = faker.internet().uuid();

		final Associate associate = this.domainsDatabuilder.getAssociateDataBuilder().build();

		when(this.findAssociateByIdUseCase.find(associateId)).thenReturn(associate);

		when(this.cpfValidatorGateway.validateCpfToEnableToVote(associate.getCpf())).thenReturn(voteCheck);

		final AssociateDisableToVoteException error = assertThrows(AssociateDisableToVoteException.class, () -> {
			this.checkIfAssociateEnableToVoteUseCase.check(associateId);
		});
		
		assertEquals(error.getCode(), "sicred.associate.error.associatedisabledtovote");
		assertEquals(error.getMessage(), "Associate disabled to vote.");
	}

}
