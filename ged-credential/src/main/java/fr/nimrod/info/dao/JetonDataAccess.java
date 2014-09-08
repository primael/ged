package fr.nimrod.info.dao;

import java.util.List;

import fr.nimrod.info.dao.impl.JetonDataAccessImplementation;
import fr.nimrod.info.model.Jeton;
import fr.nimrod.info.model.User;

public interface JetonDataAccess extends DataAccessObject<Long, Jeton> {

	List<Jeton> findTokensByUser(User utilisateur);

	static JetonDataAccess getDataAccess(){
		return JetonDataAccessImplementation.INSTANCE;
	}
	
	default String getTypeEntity() {
		return Jeton.class.getSimpleName();
	}
}
