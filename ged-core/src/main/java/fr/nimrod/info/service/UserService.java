package fr.nimrod.info.service;

import java.security.MessageDigest;
import java.util.Base64;

import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.exception.technical.GedTechnicalException;
import fr.nimrod.info.model.User;
import fr.nimrod.info.service.impl.UserServiceImplementation;

public interface UserService {

	void authenticate(String login, String password) throws GedException;
	
	User createUser(String login, String eMail, String password) throws GedException;

	User getUserByLogin(String login) throws GedException;
	
	static UserService getService(){
		return UserServiceImplementation.INSTANCE;
	}

	default byte[] base64ToByte(String data) {
		return Base64.getDecoder().decode(data);
	}

	default String byteToBase64(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}

	default byte[] getHash(int iterationNb, String password, byte[] salt) throws GedException {
		try {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset(); 
		digest.update(salt);
		byte[] input = digest.digest(password.getBytes("UTF-8"));
		for(int i = 0; i < iterationNb; i++) {
			digest.reset();
			input = digest.digest(input);
		}
		return input;
		} catch (Exception exception) {
			throw new GedTechnicalException();
		}
	}
}
