package fr.nimrod.info.service.impl;

import fr.nimrod.info.dao.AuthenticationDataAccess;
import fr.nimrod.info.model.AuthenticateToken;
import fr.nimrod.info.model.User;
import fr.nimrod.info.service.AuthenticationTokenService;
import fr.nimrod.info.trace.builders.MessageBuilder;
import fr.nimrod.info.trace.builders.security.SecurityMessageBuilder;
import fr.nimrod.info.trace.evenement.SecurityEvent;
import fr.nimrod.info.trace.operations.security.SecurityOperations;

public enum AuthenticationTokenServiceImplementation implements AuthenticationTokenService {

    INSTANCE;

    private AuthenticationDataAccess dataAccess = AuthenticationDataAccess.getDataAccess();

    @Override
    public String createAuthenticateToken(User user) {

        final AuthenticateToken token = new AuthenticateToken(user);

        MessageBuilder builder = new SecurityMessageBuilder();
        builder.with(SecurityOperations.TOKEN, token.getToken()) //
                .with(SecurityOperations.SECURITY_EVENT, SecurityEvent.TOKEN_CREATION) //
                .with(SecurityOperations.LOGIN, user.getLogin());

        System.out.println(builder.getMessage());

        // On invalide les précédents
        dataAccess.invalidateTokenFor(user);
        // On persiste le nouveau token
        dataAccess.persistToken(token);

        return token.getToken().toString();
    }

    @Override
    public boolean validateAuthenticateToken(String authenticateToken) {

        return false;
    }
}
