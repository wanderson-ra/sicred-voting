package br.com.sicred.voting.gateways.http.controllers.ruling;

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
import br.com.sicred.voting.gateways.http.controllers.ruling.json.CreateRulingRequestJson;
import br.com.sicred.voting.gateways.http.controllers.ruling.json.CreateRulingResponseJson;
import br.com.sicred.voting.usecases.CreateRulingUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${baseurl.v1}/rulings")
@Api(tags = "Consultation", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Validated
public class RulingController {

	@Autowired
	private CreateRulingUseCase createRulingUseCase;
	
	

	@ApiOperation(value = "Resource to Create Ruling", response = CreateRulingResponseJson.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 422, message = "Unprocessable Entity"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@Validated
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CreateRulingResponseJson create(
			final @RequestBody(required = true) @Valid CreateRulingRequestJson createRulingRequestJson) {
		log.info("createRulingRequestJson: {}", createRulingRequestJson);

		final Ruling rulingToCreate = Ruling.builder()
				.name(createRulingRequestJson.getName())
				.description(createRulingRequestJson.getDescription()).build();

		final Ruling rulingCreated = this.createRulingUseCase.create(rulingToCreate);

		return CreateRulingResponseJson.factory(rulingCreated.getId());

	}

}
