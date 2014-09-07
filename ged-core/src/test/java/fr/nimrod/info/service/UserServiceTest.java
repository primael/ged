package fr.nimrod.info.service;

import org.hsqldb.jdbcDriver;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import fr.nimrod.info.dao.UserDaoTest;
import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.exception.security.GedSecurityActionNotAllowedException;
import fr.nimrod.info.exception.security.GedSecurityAuthenticationFailedException;
import fr.nimrod.info.test.annotations.Data;
import fr.nimrod.info.test.annotations.Schema;
import fr.nimrod.info.test.rule.NimrodDbRule;

public class UserServiceTest {

	@Rule
	public NimrodDbRule dbUnitRule = new NimrodDbRule(UserDaoTest.class, jdbcDriver.class,
			"jdbc:hsqldb:mem:database", "sa", "");
	@Test
	public void getImplementation() {
		Assert.assertNotNull(UserService.getService());
	}

	@Test
	@Schema({ "/fr/nimrod/info/dao/user/user.sql" })
	@Data(value = { "/fr/nimrod/info/dao/user/user.json" })
	public void goodAuthenticate() throws GedException {
		UserService.getService().authenticate("Nimrod", "aqwzsx123");
	}
	
	@Test(expected=GedSecurityActionNotAllowedException.class)
	@Schema({ "/fr/nimrod/info/dao/user/user.sql" })
	@Data(value = { "/fr/nimrod/info/dao/user/user.json" })
	public void badAuthenticateByInactiveUser() throws GedException {
		UserService.getService().authenticate("l-infini", "aqwzsx123");
	}
	
	@Test(expected=GedSecurityAuthenticationFailedException.class)
	@Schema({ "/fr/nimrod/info/dao/user/user.sql" })
	@Data(value = { "/fr/nimrod/info/dao/user/user.json" })
	public void badAuthenticateWithUnknownLogin() throws GedException {
		UserService.getService().authenticate("Primael", "aqwzsx123");
	}
	
	@Test(expected=GedSecurityAuthenticationFailedException.class)
	@Schema({ "/fr/nimrod/info/dao/user/user.sql" })
	@Data(value = { "/fr/nimrod/info/dao/user/user.json" })
	public void badAuthenticateWithBadPassword() throws GedException {
		UserService.getService().authenticate("Nimrod", "azerty");
	}
	
}
