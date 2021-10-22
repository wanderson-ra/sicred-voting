package br.com.sicred.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


@SpringBootApplication
@EnableMongoAuditing
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude = {EmbeddedMongoAutoConfiguration.class})
public class SicredVotingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SicredVotingApplication.class, args);
	}

}
