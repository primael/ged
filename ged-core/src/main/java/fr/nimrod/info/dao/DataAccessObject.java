package fr.nimrod.info.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import lombok.Cleanup;
import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.exception.technical.GedTechnicalException;

@FunctionalInterface
public interface DataAccessObject<PK, E> {

	public static final String ENTITY_NAME = "ged-core";

	public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory(ENTITY_NAME);

	String getTypeEntity();

	default EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}

	@SuppressWarnings("unchecked")
	default Class<E> getClassObject() {
		return (Class<E>) this.getClass().getGenericInterfaces()[0].getClass();
	}

	default E createEntity(E entity) throws GedException {
		try {
			@Cleanup
			EntityManager entityManager = FACTORY.createEntityManager();

			entityManager.getTransaction().begin();

			entityManager.persist(entity);

			entityManager.getTransaction().commit();

			return entity;
		} catch (PersistenceException exception) {
			exception.printStackTrace();
			throw new GedTechnicalException(exception);
		}
	}

	default E readEntity(PK id) {
		return getEntityManager().find(getClassObject(), id);
	}

	@SuppressWarnings("unchecked")
	default List<E> readAllEntity() {
		@Cleanup
		EntityManager entityManager = FACTORY.createEntityManager();
		System.out.println("Select e From " + getTypeEntity() + " e");
		Query query = entityManager.createQuery("Select e From " + getTypeEntity() + " e");
		return query.getResultList();
	}

	default E updateEntity(E entity) {
		getEntityManager().getTransaction().begin();
		entity = getEntityManager().merge(entity);
		getEntityManager().getTransaction().commit();
		return entity;
	}

	default void deleteEntity(E entity) {
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(entity);
		getEntityManager().getTransaction().commit();
	}

	default Query getQuery(String jpql) {
		return getEntityManager().createQuery(jpql);
	}
}
