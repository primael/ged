package fr.nimrod.info.dao;

import org.hsqldb.jdbcDriver;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import fr.nimrod.info.config.DbUnitRule;
import fr.nimrod.info.config.DbUnitRule.Ddl;
import fr.nimrod.info.model.User;
import fr.nimrod.info.service.AuthenticationService;

public class UserDaoTest {

	//@Rule
	public DbUnitRule dbUnitRule = new DbUnitRule(UserDaoTest.class, jdbcDriver.class,
			"jdbc:hsqldb:mem:database", "sa", "");
	private UserDataAccess instanceUnderTest = UserDataAccess.getDataAccess();
	
	@Test
	@Ddl("/user.sql")
	public void createUser(){
		
		User user = AuthenticationService.getService().createUser("primael", "toto@toto.gmail", "aqwzsx123");
		
		user = instanceUnderTest.createEntity(user);
		
		System.out.println(user);
		Assert.assertNotEquals(user.getIdentifiant(), 0);
		
		User user2 = instanceUnderTest.findUserByLogin("primael");
		
		System.out.println(user2);
	}
}
