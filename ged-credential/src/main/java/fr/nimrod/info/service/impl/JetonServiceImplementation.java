package fr.nimrod.info.service.impl;

import java.util.List;

import fr.nimrod.info.dao.JetonDataAccess;
import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.model.Jeton;
import fr.nimrod.info.model.User;
import fr.nimrod.info.service.JetonService;

public enum JetonServiceImplementation implements JetonService {

	INSTANCE;

	private JetonDataAccess dao = JetonDataAccess.getDataAccess();
	
	@Override
	public Jeton createToken(User user) throws GedException {
		//On liste tous les tokens de l'utilisateur pr�c�demment cr�er		
		List<Jeton> tokens = findToken(user);
		//Pour chaques jetons trouv�es on les rends inactifs.
		tokens.stream().forEach(token->token.setActif(false));
		//On met � jour la liste des jetons
		modifyToken(tokens);
		
		//On creer un nouveau jeton
		Jeton token = new Jeton(user);
		//On le persiste
		token = dao.createEntity(token);	
		return token;
	}

	@Override
	public List<Jeton> findToken(User user) {
		return dao.findTokensByUser(user);
	}

	@Override
	public void modifyToken(List<Jeton> tokens) throws GedException {
		for(Jeton token : tokens){
			dao.updateEntity(token);
		}
	}

	@Override
	public void modifyToken(Jeton token) throws GedException {
		dao.updateEntity(token);
	}
	
}
