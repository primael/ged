package fr.nimrod.info.dao;

import fr.nimrod.info.dao.impl.CompteDataAccessImplementation;
import fr.nimrod.info.model.Compte;
import fr.nimrod.info.model.User;

public interface CompteDataAccess extends DataAccessObject<Long, Compte> {

	Compte findByUser(User user);
	
	static CompteDataAccess getDataAccess(){
		return CompteDataAccessImplementation.INSTANCE;
	}
	
	default String getTypeEntity() {
		return Compte.class.getSimpleName();
	}
	
}
