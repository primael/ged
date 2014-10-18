package fr.nimrod.info.service;

import fr.nimrod.info.model.User;
import fr.nimrod.info.service.impl.AuthenticationTokenServiceImplementation;

public interface AuthenticationTokenService {

    /**
     * Methode permettant la creation d'un jeton d'authentification. Ce Jeton est valable pour un utilisateur et une
     * duree donnee. Il ne peut pas coexister deux tokens actif pour un meme utilisateur
     * 
     * @param user
     * @return
     */
    String createAuthenticateToken(User user);

    /**
     * Methode permettant de valider un jeton d'authentification
     * 
     * @param authenticateToken
     * @return
     */
    boolean validateAuthenticateToken(String authenticateToken);

    static AuthenticationTokenService getService() {
        return AuthenticationTokenServiceImplementation.INSTANCE;
    }
}
