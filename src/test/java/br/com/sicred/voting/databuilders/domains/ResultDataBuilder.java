package br.com.sicred.voting.databuilders.domains;

import br.com.sicred.voting.databuilders.DataBuilderBase;
import br.com.sicred.voting.domains.Result;
import br.com.sicred.voting.domains.enums.VoteType;

public class ResultDataBuilder extends DataBuilderBase<Result> {

	private Result result;

	public ResultDataBuilder() {
		this.result = Result.builder()
				.winner(VoteType.NO)
				.yes(faker.number().randomDigit())
				.no(faker.number().randomDigit()).build();
	}

	public Result build() {
		return this.result;

	}

}
