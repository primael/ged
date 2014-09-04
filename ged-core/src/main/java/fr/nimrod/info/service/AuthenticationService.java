package fr.nimrod.info.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;




import fr.nimrod.info.exception.security.GedSecurityAuthenticationFailedException;
import fr.nimrod.info.model.User;
import fr.nimrod.info.service.impl.AuthenticationServiceImplementation;

public interface AuthenticationService {

	void authenticate(String login, String password) throws GedSecurityAuthenticationFailedException;
	
	User createUser(String login, String eMail, String password);

	static AuthenticationService getService(){
		return AuthenticationServiceImplementation.INSTANCE;
	}

	default byte[] base64ToByte(String data) {
		return Base64.getDecoder().decode(data);
	}

	default String byteToBase64(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}


	default byte[] getHash(int iterationNb, String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset(); 
		digest.update(salt);
		byte[] input = digest.digest(password.getBytes("UTF-8"));
		for(int i = 0; i < iterationNb; i++) {
			digest.reset();
			input = digest.digest(input);
		}
		return input;
	}
}
