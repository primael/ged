package fr.nimrod.info.service.impl;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import fr.nimrod.info.dao.TokenDataAccess;
import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.model.AuthenticationToken;
import fr.nimrod.info.model.User;
import fr.nimrod.info.model.token.TypeToken;
import fr.nimrod.info.service.abst.AbstractTokenService;
import fr.nimrod.info.trace.builders.MessageBuilder;
import fr.nimrod.info.trace.builders.security.SecurityMessageBuilder;
import fr.nimrod.info.trace.evenement.SecurityEvent;
import fr.nimrod.info.trace.operations.security.SecurityOperations;

public class AuthenticationTokenServiceImplementation extends AbstractTokenService<AuthenticationToken> {

    @SuppressWarnings("unchecked")
    @Override
    public TokenDataAccess<AuthenticationToken> getDao() {
        return (TokenDataAccess<AuthenticationToken>) TokenDataAccess.getDataAccess(TypeToken.AUTHENTIFICATIONTOKEN);
    }

    @Override
    public AuthenticationToken createToken(User utilisateur) throws GedException {
        final AuthenticationToken token = new AuthenticationToken(utilisateur);

        MessageBuilder builder = new SecurityMessageBuilder();
        builder.with(SecurityOperations.TOKEN, token.getToken()) //
                .with(SecurityOperations.SECURITY_EVENT, SecurityEvent.TOKEN_CREATION) //
                .with(SecurityOperations.LOGIN, utilisateur.getLogin());

        System.out.println(builder.getMessage());

        // On invalide les précédents
        this.getDao().invalidatePreviousTokens(utilisateur);
        // On persiste le nouveau token
        this.getDao().createEntity(token);

        return token;
    }

    @Override
    protected Duration getDuration() {
        return Duration.of(5, ChronoUnit.MINUTES);
    }

    
}
