package fr.nimrod.info.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import lombok.SneakyThrows;
import fr.nimrod.info.dao.UserDataAccess;
import fr.nimrod.info.exception.security.GedSecurityActionNotAllowedException;
import fr.nimrod.info.exception.security.GedSecurityAuthenticationFailedException;
import fr.nimrod.info.exception.technical.GedTechnicalException;
import fr.nimrod.info.model.User;
import fr.nimrod.info.model.compte.StatutUser;
import fr.nimrod.info.service.UserService;
import fr.nimrod.info.trace.builders.MessageBuilder;
import fr.nimrod.info.trace.builders.security.SecurityMessageBuilder;
import fr.nimrod.info.trace.evenement.SecurityEvent;
import fr.nimrod.info.trace.operations.security.SecurityOperations;

public enum UserServiceImplementation implements UserService {

	INSTANCE;

	private static int ITERATION_NUMBER = 1000;

	private UserDataAccess dao = UserDataAccess.getDataAccess();
	
	@Override
	@SneakyThrows
	public void authenticate(String login, String password) {
		MessageBuilder builder = new SecurityMessageBuilder();
		builder.with(SecurityOperations.SECURITY_EVENT, SecurityEvent.TRY_AUTHENTICATION);
		builder.with(SecurityOperations.LOGIN, login);
		System.out.println(builder.getMessage());
		boolean userExist = true;
		
		if (login == null || password == null) {
			userExist = false;
			login = "";
			password = "";
		}

		User user = getUserByLogin(login);

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
			MessageBuilder builder2 = new SecurityMessageBuilder();
			builder2.with(SecurityOperations.SECURITY_EVENT, SecurityEvent.AUTHENTICATION_FAILED);
			builder2.with(SecurityOperations.LOGIN, login);
			System.out.println(builder2.getMessage());
			throw new GedSecurityAuthenticationFailedException();
		}
		
		if(!isAllowedToPerformAction(user)){
			MessageBuilder builder4 = new SecurityMessageBuilder();
			builder4.with(SecurityOperations.SECURITY_EVENT, user.getStatut() == StatutUser.COMPTEBLOQUER ? SecurityEvent.USER_BLOCKED : SecurityEvent.USER_INACTIVE);
			builder4.with(SecurityOperations.LOGIN, login);
			System.out.println(builder4.getMessage());
			throw new GedSecurityActionNotAllowedException();
		}
		
		MessageBuilder builder3 = new SecurityMessageBuilder();
		builder3.with(SecurityOperations.SECURITY_EVENT, SecurityEvent.AUTHENTICATION_SUCCESS);
		builder3.with(SecurityOperations.LOGIN, login);
		System.out.println(builder3.getMessage());
	}

	@Override
	@SneakyThrows()
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
				
				User user = new User(login, eMail, password , stringSalt, stringDigest);
				
				//Par defaut le compte est inactif
				
				user.setActif(false);
				user.setStatut(StatutUser.COMPTEINACTIF);
				
				user = dao.createEntity(user);
				
				return user;
			} catch (NoSuchAlgorithmException e) {
				throw new GedTechnicalException();
			}
		} else {
			return null;
		}
	}

	@Override
	@SneakyThrows()
	public User getUserByLogin(String login) {
		User user = dao.findUserByLogin(login);
		
		//Enrichissement de l'utilisateur
		if(user != null){
			
			if(user.isActif()) {
				if(user.getNbrEssai() > 5) {
					user.setStatut(StatutUser.COMPTEBLOQUER);
				} else {
					user.setStatut(StatutUser.COMPTEACTIF);
				}
			} else {
				user.setStatut(StatutUser.COMPTEINACTIF);
			}
						
		}
		return user;
	}
	
	private boolean isAllowedToPerformAction(User user){
		return user.isActif() && user.getStatut() == StatutUser.COMPTEACTIF;
	}
}
