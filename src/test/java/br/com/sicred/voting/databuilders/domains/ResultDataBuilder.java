package br.com.sicred.voting.databuilders.domains;

import br.com.sicred.voting.databuilders.DataBuilderBase;
import br.com.sicred.voting.domains.Result;
import br.com.sicred.voting.domains.enums.Winner;

public class ResultDataBuilder extends DataBuilderBase<Result> {


	public Result build() {
		return  Result.builder()
				.winner(Winner.NO)
				.yes(faker.number().randomDigit())
				.no(faker.number().randomDigit()).build();

	}

}
