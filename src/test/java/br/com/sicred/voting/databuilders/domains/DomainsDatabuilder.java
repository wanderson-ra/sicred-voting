package br.com.sicred.voting.databuilders.domains;

import lombok.Getter;

@Getter
public class DomainsDatabuilder {

	private RulingDataBuilder rulingDataBuilder;
	private AssociateDataBuilder associateDataBuilder;
	private VolteDataBuilder volteDataBuilder;
	private VotingSessionDataBuilder votingSessionDataBuilder;
	private ResultDataBuilder resultDataBuilder;

	public DomainsDatabuilder() {
		this.rulingDataBuilder = new RulingDataBuilder();
		this.associateDataBuilder = new AssociateDataBuilder();
		this.volteDataBuilder = new VolteDataBuilder();
		this.votingSessionDataBuilder = new VotingSessionDataBuilder();
		this.resultDataBuilder = new ResultDataBuilder();
	}

}
