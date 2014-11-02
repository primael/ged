package fr.nimrod.info.service.abst;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.exception.security.GedSecurityActionTokenAllreadyUsedException;
import fr.nimrod.info.exception.security.GedSecurityActivationTokenExpiredException;
import fr.nimrod.info.exception.security.GedSecurityActivationTokenNotExistException;
import fr.nimrod.info.model.Token;
import fr.nimrod.info.service.TokenService;
import fr.nimrod.info.tools.TimeTools;

public abstract class AbstractTokenService<T extends Token> implements TokenService<T> {

    /**
     * Un token est valide si: <br/>
     * <ul>
     * <li>il a �t� pr�cedemmment cr�er.</li>
     * <li>il n'a pas d�j� �t� utilis�.</li>
     * <li>il n'a pas d�pass� le temps imparti.</li>
     * <li>l'utilisateur est en compte inactif.</li>
     * </ul>
     * @throws GedException 
     */
    @Override
    public void validateToken(String token) throws GedException {
        
        Token activationToken = getDao().findTokenByToken(token);
        
        //V�rification de l'existence du token
        if(activationToken==null){
            throw new GedSecurityActivationTokenNotExistException();
        }
        
        //V�rification du d�lai du token
        if(!TimeTools.INSTANCE.validateExpirationTime(activationToken.getTimeStamp(), getDuration())){
          throw new GedSecurityActivationTokenExpiredException();
        }
        
        //Validation de l'activation du jeton
        if(!activationToken.isValid()) {
            throw new GedSecurityActionTokenAllreadyUsedException();
        }
    }

    protected Duration getDuration() {
        return Duration.of(1, ChronoUnit.DAYS);
    }

}
