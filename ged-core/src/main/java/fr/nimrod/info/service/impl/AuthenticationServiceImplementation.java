package fr.nimrod.info.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import lombok.SneakyThrows;
import fr.nimrod.info.dao.UserDataAccess;
import fr.nimrod.info.exception.security.GedSecurityAuthenticationFailedException;
import fr.nimrod.info.model.User;
import fr.nimrod.info.service.AuthenticationService;
import fr.nimrod.info.trace.builders.MessageBuilder;
import fr.nimrod.info.trace.builders.security.SecurityMessageBuilder;
import fr.nimrod.info.trace.evenement.SecurityEvent;
import fr.nimrod.info.trace.operations.security.SecurityOperations;

public enum AuthenticationServiceImplementation implements AuthenticationService {

	INSTANCE;

	private static int ITERATION_NUMBER = 1000;

	@Override
	@SneakyThrows
	public void authenticate(String login, String password) {
		MessageBuilder builder = new SecurityMessageBuilder();
		builder.with(SecurityOperations.SECURITY_EVENT, SecurityEvent.TRY_AUTHENTICATION);
		System.out.println(builder.getMessage());
		try {
			boolean userExist = true;

			if (login == null || password == null) {
				userExist = false;
				login = "";
				password = "";
			}

			User user = UserDataAccess.getDataAccess().findUserByLogin(login);

			if (user == null) {
				user = new User();
				user.setHash("000000000000000000000000000=");
				user.setSalt("00000000000=");
				userExist = false;
			}

			byte[] byteDigest = base64ToByte(user.getHash());
			byte[] byteSalt = base64ToByte(user.getSalt());

			byte[] proposedDigest = getHash(ITERATION_NUMBER, password, byteSalt);

			if(!( Arrays.equals(proposedDigest, byteDigest) && userExist)){
				throw new GedSecurityAuthenticationFailedException();
			}
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException exception) {
			System.err.println(exception);
			// Erreur lors du hash
			throw new Exception();
		}
	}

	@Override
	@SneakyThrows
	public User createUser(String login, String eMail, String password) {
		// FIXME ajouter des contrôles de format?
		if (login != null && password != null) {
			try {
				SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
				byte[] byteSalt = new byte[8];
				random.nextBytes(byteSalt);
				
				byte[] byteDigest = getHash(ITERATION_NUMBER, password, byteSalt);
				String stringDigest = byteToBase64(byteDigest);
				String stringSalt = byteToBase64(byteSalt);
				
				return new User(login, eMail, password.toCharArray() , stringSalt, stringDigest);
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				throw new Exception();
			}
		} else {
			return null;
		}
	}

}
