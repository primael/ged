package fr.nimrod.info.service;

import org.hsqldb.jdbcDriver;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import fr.nimrod.info.dao.UserDaoTest;
import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.exception.security.GedSecurityAuthenticationFailedException;
import fr.nimrod.info.test.annotations.Data;
import fr.nimrod.info.test.annotations.Schema;
import fr.nimrod.info.test.rule.NimrodDbRule;

public class AuthenticateTest {

	@Rule
	public NimrodDbRule dbUnitRule = new NimrodDbRule(UserDaoTest.class, jdbcDriver.class,
			"jdbc:hsqldb:mem:database", "sa", "");
	@Test
	public void getImplementation() {
		Assert.assertNotNull(AuthenticationService.getService());
	}

	@Test
	@Schema({ "/user.sql" })
	@Data(value = { "/user.json" })
	public void goodAuthenticate() throws GedException {
		AuthenticationService.getService().authenticate("Nimrod", "aqwzsx123");
	}
	
	@Test(expected=GedSecurityAuthenticationFailedException.class)
	@Schema({ "/user.sql" })
	@Data(value = { "/user.json" })
	public void badAuthenticateWithUnknownLogin() throws GedException {
		AuthenticationService.getService().authenticate("Primael", "aqwzsx123");
	}
	
	
}
