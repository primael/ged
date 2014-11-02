package fr.nimrod.info.service.impl;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import javax.transaction.Transactional;

import fr.nimrod.info.dao.TokenDataAccess;
import fr.nimrod.info.dao.UserDataAccess;
import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.exception.security.GedSecurityActionNotAllowedException;
import fr.nimrod.info.exception.security.GedSecurityActionTokenAllreadyUsedException;
import fr.nimrod.info.exception.security.GedSecurityActivationTokenExpiredException;
import fr.nimrod.info.exception.security.GedSecurityActivationTokenNotExistException;
import fr.nimrod.info.model.ActivationToken;
import fr.nimrod.info.model.Token;
import fr.nimrod.info.model.User;
import fr.nimrod.info.model.token.TypeToken;
import fr.nimrod.info.service.UserService;
import fr.nimrod.info.service.abst.AbstractTokenService;
import fr.nimrod.info.tools.TimeTools;

public class ActivationTokenServiceImplementation extends AbstractTokenService<ActivationToken> {

    private UserDataAccess userDao = UserDataAccess.getDataAccess();
    private UserService userService = UserService.getService();
    
    @Override
    public ActivationToken createToken(User utilisateur) throws GedException {
        ActivationToken activationToken = new ActivationToken(utilisateur);
        getDao().createEntity(activationToken);
        return activationToken;
    }

    @Override
    @Transactional
    public void validateToken(String token) throws GedException {
        Token activationToken = getDao().findTokenByToken(token);
        
        //Vérification de l'existence du token
        if(activationToken==null){
            throw new GedSecurityActivationTokenNotExistException();
        }
        
        //Vérification du délai du token
        if(!TimeTools.INSTANCE.validateExpirationTime(activationToken.getTimeStamp(), Duration.of(1, ChronoUnit.DAYS))){
          throw new GedSecurityActivationTokenExpiredException();
        }
        
        //Vérification de la validité du token (pas déjà utilisé)
        if(!activationToken.isValid()){
            throw new GedSecurityActionTokenAllreadyUsedException();
        }
        
        //Activation de l'utilisateur
        try {
            User utilisateur = userService.getUserByLogin(activationToken.getUtilisateur().getLogin());
            utilisateur.getStatut().activerCompte();
            utilisateur.setActif(true);
            
            userDao.updateEntity(utilisateur);
            
            activationToken.setValid(false);
            
            getDao().updateEntity((ActivationToken) activationToken);
            
        } catch(IllegalStateException exception) {
            throw new GedSecurityActionNotAllowedException();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public TokenDataAccess<ActivationToken> getDao() {
        return (TokenDataAccess<ActivationToken>) TokenDataAccess.getDataAccess(TypeToken.ACTIVATIONTOKEN);
    }
}
