package br.com.sicred.voting.databuilders.domains;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import br.com.sicred.voting.databuilders.DataBuilderBase;
import br.com.sicred.voting.databuilders.Utils;
import br.com.sicred.voting.domains.Associate;

public class AssociateDataBuilder extends DataBuilderBase<Associate> {

	private String id;
	private LocalDateTime createdAt;
	private LocalDateTime lastUpdate;

	public AssociateDataBuilder() {
		this.id = this.faker.internet().uuid();
		this.createdAt = Utils.convertToLocalDateTime(this.faker.date().past(2, TimeUnit.DAYS));
		this.lastUpdate = Utils.convertToLocalDateTime(this.faker.date().past(2, TimeUnit.DAYS));		
	}
	
	public AssociateDataBuilder toCreate() {
		this.id = null;
		this.createdAt = null;
		this.lastUpdate = null;
			
		return this;
	}

	public Associate build() {
		return Associate.builder()
				.id(id)
				.name(this.faker.name().fullName())
				.cpf(this.faker.internet().uuid())
				.createdAt(createdAt)
				.lastUpdate(lastUpdate)
				.build();
	}

}
