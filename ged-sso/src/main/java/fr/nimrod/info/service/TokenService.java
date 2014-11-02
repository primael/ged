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
     * Méthode permettant de valider un jeton d'activation
     * @param token Le jeton d'activation à valider
     * @throws GedException Erreur levée lorsqu'un état incompatible est trouvé.
     */
    void validateToken(String token) throws GedException;

    /**
     * Méthode permettant la création d'un jeton d'activation
     * @param utilisateur
     * @return
     * @throws GedException 
     */
    T createToken(User utilisateur) throws GedException;

    static TokenService<? extends Token> getService(TypeToken typeToken) {
        return typeToken.getService();
    }

}
