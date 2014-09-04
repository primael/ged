package fr.nimrod.info.service;


import org.junit.Assert;
import org.junit.Test;

import fr.nimrod.info.exception.security.GedSecurityAuthenticationFailedException;

public class AuthenticateTest {

	@Test
	public void getImplementation(){
		Assert.assertNotNull(AuthenticationService.getService());
	}
	
	@Test
	public void goodAuthenticate(){
		try {
			AuthenticationService.getService().authenticate("primael", "aqwzsx123");
		} catch (GedSecurityAuthenticationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
