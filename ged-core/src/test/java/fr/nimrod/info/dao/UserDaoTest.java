package fr.nimrod.info.dao;

import org.hsqldb.jdbcDriver;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.model.User;
import fr.nimrod.info.service.AuthenticationService;
import fr.nimrod.info.test.annotations.Data;
import fr.nimrod.info.test.annotations.DataExpected;
import fr.nimrod.info.test.annotations.Schema;
import fr.nimrod.info.test.rule.NimrodDbRule;

public class UserDaoTest {

	@Rule
	public NimrodDbRule dbUnitRule = new NimrodDbRule(UserDaoTest.class, jdbcDriver.class,
			"jdbc:hsqldb:mem:database", "sa", "");
	
	private UserDataAccess instanceUnderTest = UserDataAccess.getDataAccess();
	
	@Test(expected=Exception.class)
	@Schema({"/user.sql"})
	@Data(value = {"/user.json"} )
	public void createUser() throws GedException {
		
		User user = AuthenticationService.getService().createUser("primael", "toto@toto.gmail", "aqwzsx123");
		user = instanceUnderTest.createEntity(user);
		User user2 = instanceUnderTest.findUserByLogin("primael");
		
		Assert.assertNotEquals(user.getIdentifiant(), 0);
		Assert.assertEquals(user, user2);
		
		User user3 = AuthenticationService.getService().createUser("Nimrod", "toto@toto.gmail", "aqwzsx123");
		System.out.println(user3);
		user3 = instanceUnderTest.createEntity(user3);
		User user4 = AuthenticationService.getService().createUser("Nimrod", "toto@toto.gmail", "aqwzsx123");
		System.out.println(user4);
		
		instanceUnderTest.readAllEntity().stream().forEach(userFound -> System.out.println(userFound));
	}
	
	@Test
	@Schema({"/user.sql"})
	@Data(value = {"/user.json"} )
	public void findUser(){
		User user = instanceUnderTest.findUserByLogin("Nimrod");
		
		Assert.assertEquals("identifiant", user.getIdentifiant(), 1);
		Assert.assertEquals("identifiant", user.getLogin(), "Nimrod");
		Assert.assertEquals("identifiant", user.getEmail(), "primael@nimrod-info.fr");
		Assert.assertEquals("identifiant", user.getSalt(), "XNv44yasUQ0=");
		Assert.assertEquals("identifiant", user.getHash(), "JqhITW+SURu6tNbhxa7GE7uzXeQ=");
		
		user = instanceUnderTest.findUserByLogin("l-infini");
		
		Assert.assertEquals("identifiant", user.getIdentifiant(), 2);
		Assert.assertEquals("identifiant", user.getLogin(), "l-infini");
		Assert.assertEquals("identifiant", user.getEmail(), "primael@l-infini.fr");
		Assert.assertEquals("identifiant", user.getSalt(), "1c4j4ACmchA=");
		Assert.assertEquals("identifiant", user.getHash(), "5KtC1hCScIF+F7xpa2fCeDWzpcc=");
	}
	
	@Test
	@Schema({"/user.sql"})
	@Data(value = {"/user.json"})
	@DataExpected(file="/user-expected.json", tableName="utilisateur", orderBy="identifiant", ignoredColumn={"hash","salt"} )
	public void listUser(){
		instanceUnderTest.readAllEntity().stream().forEach(user->System.out.println(user));
	}
}
