package fr.nimrod.info.dao;

import org.hsqldb.jdbcDriver;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.model.User;
import fr.nimrod.info.service.UserService;
import fr.nimrod.info.test.annotations.Data;
import fr.nimrod.info.test.annotations.DataExpected;
import fr.nimrod.info.test.annotations.Schema;
import fr.nimrod.info.test.rule.NimrodDbRule;

public class UserDaoTest {

	@Rule
	public NimrodDbRule dbUnitRule = new NimrodDbRule(UserDaoTest.class, jdbcDriver.class,
			"jdbc:hsqldb:mem:database", "sa", "");
	
	private UserDataAccess instanceUnderTest = UserDataAccess.getDataAccess();
	
	@Test(expected=GedException.class)
	@Schema({"/fr/nimrod/info/dao/user/user.sql"})
	public void createDuplicateMailUser() throws GedException {
		User user = UserService.getService().createUser("primael", "primaelbruant@gmail.com", "azerty");
		UserDataAccess.getDataAccess().createEntity(user);
		User badUser = UserService.getService().createUser("nimrod", "primaelbruant@gmail.com", "azerty");
		//throw an exception
		UserDataAccess.getDataAccess().createEntity(badUser);
	}
	
	@Test(expected=GedException.class)
	@Schema({"/fr/nimrod/info/dao/user/user.sql"})
	public void createDuplicateUser() throws GedException {
		User user = UserService.getService().createUser("primael", "primaelbruant@gmail.com", "azerty");
		UserDataAccess.getDataAccess().createEntity(user);
		User badUser = UserService.getService().createUser("primael", "primaelbruant@gmail.com", "azerty");
		//throw an exception
		UserDataAccess.getDataAccess().createEntity(badUser);
		instanceUnderTest.readAllEntity().stream().forEach(userItem->System.out.println(userItem));
	}

	@Test(expected=GedException.class)
	@Schema({"/fr/nimrod/info/dao/user/user.sql"})
	public void createDuplicateLoginUser() throws GedException {
		User user = UserService.getService().createUser("primael", "primaelbruant@gmail.com", "azerty");
		UserDataAccess.getDataAccess().createEntity(user);
		User badUser = UserService.getService().createUser("primael", "primael@nimrod-info.fr", "azerty");
		//throw an exception
		UserDataAccess.getDataAccess().createEntity(badUser);
		
	}
	
	@Test(expected=GedException.class)
	@Schema({"/fr/nimrod/info/dao/user/user.sql"})
	public void createUserWithNoSalt() throws GedException {
		User user = UserService.getService().createUser("primael", "primaelbruant@gmail.com", "azerty");
		user.setSalt(null);
		UserDataAccess.getDataAccess().updateEntity(user);
	}

	@Test(expected=GedException.class)
	@Schema({"/fr/nimrod/info/dao/user/user.sql"})
	public void createUserWithNoHash() throws GedException {
		User user = UserService.getService().createUser("primael", "primaelbruant@gmail.com", "azerty");
		user.setHash(null);
		UserDataAccess.getDataAccess().updateEntity(user);
	}
	
	@Test
	@Schema({"/fr/nimrod/info/dao/user/user.sql"})
	@Data(value = {"/fr/nimrod/info/dao/user/user.json"} )
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
	@Schema({"/fr/nimrod/info/dao/user/user.sql"})
	@Data(value = {"/fr/nimrod/info/dao/user/user.json"})
	@DataExpected(file="/fr/nimrod/info/dao/user/user-expected.json", tableName="utilisateur", ignoredColumn={"hash","salt","identifiant"} )
	public void createUser() throws GedException{
		UserService.getService().createUser("primael", "primaelbruant@gmail.com", "azerty");
		instanceUnderTest.readAllEntity().stream().forEach(user->System.out.println(user));
	}
}
