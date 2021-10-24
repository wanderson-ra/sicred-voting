package br.com.sicred.voting.gateways.http.controllers.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.com.sicred.voting.domains.Result;
import br.com.sicred.voting.gateways.http.controllers.result.json.GetResultResponseJson;
import br.com.sicred.voting.usecases.GetResultVotingSessionUseCase;
import br.com.sicred.voting.utils.BaseTest;

public class ResultControllerUniTest extends BaseTest{
	
	@InjectMocks
	private ResultController resultController;
	
	@Mock
	private GetResultVotingSessionUseCase getResultVotingSessionUseCase;
	
	@Test
	@DisplayName("Should by get result with success")
	public void ShouldByResultSuccess() {
		final String rulingId = this.faker.internet().uuid();
		final String votingSessionId = this.faker.internet().uuid();
		
		final Result result = this.domainsDatabuilder.getResultDataBuilder().build();		
		
		when(this.getResultVotingSessionUseCase.getResult(rulingId, votingSessionId)).thenReturn(result);
		
		final GetResultResponseJson responseJson = this.resultController.getResult(rulingId, votingSessionId);
		
		assertEquals(result.getNo(), responseJson.getNo());
		assertEquals(result.getYes(), responseJson.getYes());
		assertEquals(result.getWinner(), responseJson.getWinner());		
	}
}
