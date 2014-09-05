package fr.nimrod.info.dao;

import org.hsqldb.jdbcDriver;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import com.mysql.jdbc.Driver;

import fr.nimrod.info.config.DbUnitRule;
import fr.nimrod.info.config.DbUnitRule.Data;
import fr.nimrod.info.config.DbUnitRule.Schema;
import fr.nimrod.info.model.User;
import fr.nimrod.info.service.AuthenticationService;

public class UserDaoTest {

	@Rule
	public DbUnitRule dbUnitRule = new DbUnitRule(UserDaoTest.class, jdbcDriver.class,
			"jdbc:hsqldb:mem:database", "sa", "");
	
//	public DbUnitRule dbUnitRule = new DbUnitRule(UserDaoTest.class, Driver.class,
//			"jdbc:mysql://localhost/ged", "root", "");
	
	private UserDataAccess instanceUnderTest = UserDataAccess.getDataAccess();
	
	@Test
	@Schema("/user.sql")
	public void createUser(){
		
		User user = AuthenticationService.getService().createUser("primael", "toto@toto.gmail", "aqwzsx123");
		user = instanceUnderTest.createEntity(user);
		User user2 = instanceUnderTest.findUserByLogin("primael");
		
		Assert.assertNotEquals(user.getIdentifiant(), 0);
		Assert.assertEquals(user, user2);
	}
	
	@Test
	@Schema("/user.sql")
	@Data(values = {"/user.json"} )
	public void findUser(){
		User user = instanceUnderTest.findUserByLogin("Nimrod");
		System.out.println(user);
		
		instanceUnderTest.readAllEntity().stream().forEach(utilisateur -> System.out.println(utilisateur));
	}
}
