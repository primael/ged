package fr.nimrod.info.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import lombok.Cleanup;
import fr.nimrod.info.dao.UserDataAccess;
import fr.nimrod.info.model.User;

public enum UserDataAccessImplementation implements UserDataAccess {

	INSTANCE;

	@Override
	public void persistUser() {
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User findUserByLogin(String login) {
		@Cleanup
		EntityManager entityManager = FACTORY.createEntityManager();
		Query query = entityManager.createQuery("Select u From User u Where u.login = :login");
		query.setParameter("login", login);
		
		return (User) query.getResultList().stream().findFirst().orElse(null);
	}

}
