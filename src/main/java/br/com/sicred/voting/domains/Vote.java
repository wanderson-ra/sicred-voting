package br.com.sicred.voting.domains;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import br.com.sicred.voting.domains.enums.VoteType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@Document
public class Vote {
	
	@Id
	private String id;
	
	@NonNull
	private VoteType voteType;	

	@NonNull
	@DBRef
	private Associate associate;
	
	@NonNull
	@DBRef
	private VotingSession votingSession;

	@CreatedDate
	private LocalDateTime createdAt;	

}
