package fr.nimrod.info.trace.builders;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;

import fr.nimrod.info.trace.builders.security.SecurityMessageBuilder;
import fr.nimrod.info.trace.operations.security.SecurityOperations;

public class MessageBuilderTest {

	@Test
	public void messageConstruct(){
		MessageBuilder messageBuilder = new SecurityMessageBuilder();
		
		messageBuilder //
			.with(SecurityOperations.TOKEN, UUID.randomUUID()) //
			.with(SecurityOperations.TIMESTAMP, LocalDateTime.now());
		
		String message = messageBuilder.getMessage();
		
		System.out.println(message);
		
		
	}
}
