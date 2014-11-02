package fr.nimrod.info.service;

import fr.nimrod.info.dao.TokenDataAccess;
import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.model.Token;
import fr.nimrod.info.model.User;
import fr.nimrod.info.model.token.TypeToken;

/**
 * Service permettant la gestion des token d'activation
 * @author Primael Bruant
 *
 */
public interface TokenService<T extends Token> {

    TokenDataAccess<T> getDao();
    
    /**
     * M�thode permettant de valider un jeton d'activation
     * @param token Le jeton d'activation � valider
     * @throws GedException Erreur lev�e lorsqu'un �tat incompatible est trouv�.
     */
    void validateToken(String token) throws GedException;

    /**
     * M�thode permettant la cr�ation d'un jeton d'activation
     * @param utilisateur
     * @return
     * @throws GedException 
     */
    T createToken(User utilisateur) throws GedException;

    static TokenService<? extends Token> getService(TypeToken typeToken) {
        return typeToken.getService();
    }

}
