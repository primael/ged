package fr.nimrod.info.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import lombok.Cleanup;
import fr.nimrod.info.dao.CompteDataAccess;
import fr.nimrod.info.model.Compte;
import fr.nimrod.info.model.User;

public enum CompteDataAccessImplementation implements CompteDataAccess {

    INSTANCE;

    @SuppressWarnings("unchecked")
    @Override
    public Compte findByUser(User user) {
        @Cleanup
        EntityManager entityManager = FACTORY.createEntityManager();
        Query query = entityManager.createQuery("Select c From Compte c Where c.utilisateur = :user");
        query.setParameter("user", user);

        return (Compte) query.getResultList().stream().findFirst().orElse(null);
    }
}
