package fr.nimrod.info.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import lombok.Cleanup;
import fr.nimrod.info.dao.JetonDataAccess;
import fr.nimrod.info.model.Jeton;
import fr.nimrod.info.model.User;

public enum JetonDataAccessImplementation implements JetonDataAccess {

	INSTANCE;

	@SuppressWarnings("unchecked")
	@Override
	public List<Jeton> findTokensByUser(User utilisateur) {
		@Cleanup
		EntityManager entityManager = FACTORY.createEntityManager();
		Query query = entityManager.createQuery("Select j From Jeton j Where j.utilisateur = :utilisateur");
		query.setParameter("utilisateur", utilisateur);
		
		return query.getResultList();
	}
	
}
