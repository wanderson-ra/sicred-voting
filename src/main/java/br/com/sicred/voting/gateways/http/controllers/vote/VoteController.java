package br.com.sicred.voting.gateways.http.controllers.vote;

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

import br.com.sicred.voting.domains.Associate;
import br.com.sicred.voting.domains.Ruling;
import br.com.sicred.voting.domains.Vote;
import br.com.sicred.voting.gateways.http.controllers.vote.json.CreateVoteRequestJson;
import br.com.sicred.voting.gateways.http.controllers.vote.json.CreateVoteResponseJson;
import br.com.sicred.voting.usecases.CreateVoteUseCaseOrquestrator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${baseurl.v1}/votes")
@Api(tags = "Vote", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Validated
public class VoteController {
	
	@Autowired
	private CreateVoteUseCaseOrquestrator createVoteUseCaseOrquestrator;
	
	
	@ApiOperation(value = "Resource to Create Vote", response = CreateVoteResponseJson.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 422, message = "Unprocessable Entity"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@Validated
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CreateVoteResponseJson create(
			final @RequestBody(required = true) @Valid CreateVoteRequestJson createVoteRequestJson) {
		log.info("createVoteRequestJson: {}", createVoteRequestJson);

		final Vote voteToCreate = this.mapperVoteFromCreateVoteRequestJson(createVoteRequestJson);		
	
		final Vote voteCreated = this.createVoteUseCaseOrquestrator.create(voteToCreate);
		
		return CreateVoteResponseJson.builder().id(voteCreated.getId()).build();

	}


	private Vote mapperVoteFromCreateVoteRequestJson(final CreateVoteRequestJson createVoteRequestJson) {
		
		final Associate associate = Associate.builder()
				.id(createVoteRequestJson.getAssociateId())
				.build();
		
		final Ruling ruling = Ruling.builder()
				.id(createVoteRequestJson.getRulingId())
				.build();
		
		
		final Vote voteToCreate = Vote.builder()
				.associate(associate).ruling(ruling)
				.voteType(createVoteRequestJson.getVoteType())
				.build();
		
		return voteToCreate;
	}

}
