package br.com.sicred.voting.gateways.http.controllers.votingsession;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicred.voting.domains.Ruling;
import br.com.sicred.voting.domains.VotingSession;
import br.com.sicred.voting.gateways.http.controllers.votingsession.json.OpenVotingSessionRequestJson;
import br.com.sicred.voting.gateways.http.controllers.votingsession.json.OpenVotingSessionResponseJson;
import br.com.sicred.voting.usecases.OpenVotingSessionUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${baseurl.v1}/votingsessions")
@Api(tags = "VotingSession", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Validated
public class VotingSessionController {
	
	@Autowired
	private OpenVotingSessionUseCase openVotingSessionUseCase;
	
	@ApiOperation(value = "Resource to open voting session", response = OpenVotingSessionResponseJson.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 422, message = "Unprocessable Entity"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@Validated
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("open")
	public OpenVotingSessionResponseJson openVotingSession(
			final @RequestBody(required = true) @Valid OpenVotingSessionRequestJson openVotingSessionRequestJson) {
		log.info("openVotingSessionRequestJson: {}", openVotingSessionRequestJson);
		
		final VotingSession votingSessionToOpen = this.mapperVotingSessionFromOpenSessionRequesJson(
				openVotingSessionRequestJson);
		
		final VotingSession votingSessionOpened = this.openVotingSessionUseCase.open(votingSessionToOpen);
		
		return OpenVotingSessionResponseJson.builder().id(votingSessionOpened.getId()).build();
		
	}

	private VotingSession mapperVotingSessionFromOpenSessionRequesJson(
			final OpenVotingSessionRequestJson openVotingSessionRequestJson) {
		
		final Ruling ruling = Ruling.builder()
				.id(openVotingSessionRequestJson.getRulingId())
				.build();			
		
		VotingSession votingSessionToOpen = VotingSession.builder()			
					.expiration(openVotingSessionRequestJson.getExpiration())					
					.ruling(ruling)
					.build();		
		
		return votingSessionToOpen;
	}

}
