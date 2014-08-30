package fr.nimrod.info.dao.impl;

import fr.nimrod.info.dao.UserDataAccess;
import fr.nimrod.info.model.User;

public enum UserDataAccessImplementation implements UserDataAccess {

	INSTANCE;

	@Override
	public void persistUser() {
	
	}

	@Override
	public User findUserByLogin(String login) {
	
		return null;
	}

}
