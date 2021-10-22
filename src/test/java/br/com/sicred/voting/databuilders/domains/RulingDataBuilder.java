package br.com.sicred.voting.databuilders.domains;

import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.concurrent.TimeUnit;


import br.com.sicred.voting.databuilders.DataBuilderBase;
import br.com.sicred.voting.databuilders.Utils;
import br.com.sicred.voting.domains.Ruling;

public class RulingDataBuilder extends DataBuilderBase<Ruling> {

	private Ruling ruling;

	public RulingDataBuilder() {

		this.ruling = Ruling.builder()
				.id(this.faker.internet().uuid())
				.name(this.faker.company().name())
				.description(this.faker.lorem().paragraph())
				.createdAt(Utils.convertToLocalDateTime(this.faker.date().past(1, TimeUnit.DAYS)))
				.lastUpdate(Utils.convertToLocalDateTime(this.faker.date().past(1, TimeUnit.DAYS)))
				.build();
				
	}

	public RulingDataBuilder toCreate() {
		setField(this.ruling, "id", null);	;
		setField(this.ruling, "createdAt", null);
		setField(this.ruling, "lastUpdate", null);
		
		return this;
	}
	

	public Ruling build() {
		return this.ruling;
	}

}
