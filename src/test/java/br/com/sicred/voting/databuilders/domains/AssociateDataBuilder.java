package br.com.sicred.voting.databuilders.domains;


import java.util.concurrent.TimeUnit;

import br.com.sicred.voting.databuilders.DataBuilderBase;
import br.com.sicred.voting.databuilders.Utils;
import br.com.sicred.voting.domains.Associate;

public class AssociateDataBuilder extends DataBuilderBase<Associate> {

	private Associate associate;

	public AssociateDataBuilder() {

		this.associate = Associate.builder()
				.id(this.faker.internet().uuid())
				.name(this.faker.name().fullName())
				.cpf(this.faker.internet().uuid())
				.createdAt(Utils.convertToLocalDateTime(this.faker.date().past(2, TimeUnit.DAYS)))
				.lastUpdate(Utils.convertToLocalDateTime(this.faker.date().past(2, TimeUnit.DAYS)))
				.build();
	}

	public Associate build() {
		return this.associate;
	}

}
