package br.com.sicred.voting.usecases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.usecases.exceptions.VotingSessionIsClosedException;
import br.com.sicred.voting.utils.BaseTest;

public class ChekVotingSessionIsOpenUseCaseUniTest extends BaseTest {

	@InjectMocks
	private ChekVotingSessionIsOpenUseCase chekVotingSessionIsOpenUseCase;

	@Mock
	private FindVotingSessionByIdUseCase findVotingSessionByIdUseCase;

	@Test
	@DisplayName("Should by voting session is open")
	public void ShouldBySessionIsOpen() {

		final String votingSessionId = this.faker.internet().uuid();
		
		final LocalDateTime localDateTimeNotExpiration = LocalDateTime.now().plusDays(10);

		final VotingSession votingSessionOpened = this.domainsDatabuilder.getVotingSessionDataBuilder()
				.setExpiration(localDateTimeNotExpiration).build();		

		when(this.findVotingSessionByIdUseCase.find(votingSessionId)).thenReturn(votingSessionOpened);

		this.chekVotingSessionIsOpenUseCase.check(votingSessionId);

		verify(this.findVotingSessionByIdUseCase, VerificationModeFactory.times(1)).find(votingSessionId);
	}

	@Test
	@DisplayName("Should by voting session is closed")
	public void ShouldBySessionIsClosed() {

		final String votingSessionId = this.faker.internet().uuid();
		
		final LocalDateTime localDateTimeExpiration = LocalDateTime.now().minusDays(10);

		final VotingSession votingSessionOpened = this.domainsDatabuilder.getVotingSessionDataBuilder()
				.setExpiration(localDateTimeExpiration).build();	
		

		when(this.findVotingSessionByIdUseCase.find(votingSessionId)).thenReturn(votingSessionOpened);

		final VotingSessionIsClosedException error = assertThrows(VotingSessionIsClosedException.class, () -> {
			this.chekVotingSessionIsOpenUseCase.check(votingSessionId);
		});

		assertEquals(error.getCode(), "sicred.votingsession.error.closed");
		assertEquals(error.getMessage(), "Voting Session is closed.");
	}

}
