package com.example.bank;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	AgentBuilder.RawMatcher.Inversion inversion;

}
