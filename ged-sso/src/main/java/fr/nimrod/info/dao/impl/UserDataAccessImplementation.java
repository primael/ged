package fr.nimrod.info.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import lombok.Cleanup;
import fr.nimrod.info.dao.UserDataAccess;
import fr.nimrod.info.model.User;

public enum UserDataAccessImplementation implements UserDataAccess {

    INSTANCE;

    @SuppressWarnings("unchecked")
    @Override
    public User findUserByLogin(String login) {
        @Cleanup
        EntityManager entityManager = FACTORY.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select u From User u Where u.login = :login");
        query.setParameter("login", login);

        User utilisateur = (User) query.getResultList().stream().findFirst().orElse(null);
        entityManager.getTransaction().commit();
        
        return utilisateur;
    }

    @SuppressWarnings("unchecked")
    @Override
    public User findUserByMail(String email) {
        @Cleanup
        EntityManager entityManager = FACTORY.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select u From User u Where u.email = :email");
        query.setParameter("email", email);

        User utilisateur = (User) query.getResultList().stream().findFirst().orElse(null);
        entityManager.getTransaction().commit();
        
        return utilisateur;
    }

}
