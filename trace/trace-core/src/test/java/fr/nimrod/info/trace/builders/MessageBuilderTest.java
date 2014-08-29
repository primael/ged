package fr.nimrod.info.trace.builders;

import java.util.UUID;

import org.junit.Test;

import fr.nimrod.info.trace.builders.security.SecurityMessageBuilder;
import fr.nimrod.info.trace.evenement.SecurityEvent;
import fr.nimrod.info.trace.operations.security.SecurityOperations;

public class MessageBuilderTest {

	@Test
	public void messageConstruct(){
		MessageBuilder messageBuilder = new SecurityMessageBuilder();
		
		messageBuilder //
			.with(SecurityOperations.TOKEN, UUID.randomUUID()) //
			.with(SecurityOperations.TOKEN, ((Object[])null)) //
			.with(SecurityOperations.SECURITY_EVENT, SecurityEvent.TRY_AUTHENTICATION);
		
		String message = messageBuilder.getMessage();
		
		System.out.println(message);
		
	}
}
