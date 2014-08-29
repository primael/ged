package fr.nimrod.info.service;


import org.junit.Assert;
import org.junit.Test;

public class AuthenticateTest {

	@Test
	public void getImplementation(){
		Assert.assertNotNull(AuthenticationService.getService());
	}
	
	@Test
	public void goodAuthenticate(){
		//User utilisateur = AuthenticationService.getService().createUser("primael", "aqwzsx123");
		
		AuthenticationService.getService().authenticate("primael", "aqwzsx123");
	}
}
