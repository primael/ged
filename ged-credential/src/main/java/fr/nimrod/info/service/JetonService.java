package fr.nimrod.info.service;

import java.util.List;

import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.model.Jeton;
import fr.nimrod.info.model.User;
import fr.nimrod.info.service.impl.JetonServiceImplementation;

public interface JetonService {

	static JetonService getService(){
		return JetonServiceImplementation.INSTANCE;
	}
	
	Jeton createToken(User user) throws GedException;
	
	List<Jeton> findToken(User user) ;
	
	void modifyToken(List<Jeton> tokens) throws GedException;
	
	void modifyToken(Jeton token) throws GedException;
	
}
