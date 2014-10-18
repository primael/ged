package fr.nimrod.info.dao;

import fr.nimrod.info.dao.impl.AuthenticationDataAccessImplementation;
import fr.nimrod.info.model.AuthenticateToken;
import fr.nimrod.info.model.User;

public interface AuthenticationDataAccess {

    static AuthenticationDataAccess getDataAccess() {
        return AuthenticationDataAccessImplementation.INSTANCE;
    }

    void invalidateTokenFor(User user);

    void persistToken(AuthenticateToken token);
}
