package fr.nimrod.info.model.token;

import lombok.Getter;
import fr.nimrod.info.dao.TokenDataAccess;
import fr.nimrod.info.dao.impl.AuthenticationDataAccessImplementation;
import fr.nimrod.info.dao.impl.TokenDataAccessImplementation;
import fr.nimrod.info.model.ActivationToken;
import fr.nimrod.info.model.Token;
import fr.nimrod.info.service.TokenService;
import fr.nimrod.info.service.impl.ActivationTokenServiceImplementation;
import fr.nimrod.info.service.impl.AuthenticationTokenServiceImplementation;

@Getter
public enum TypeToken {

    ACTIVATIONTOKEN(new ActivationTokenServiceImplementation(), new TokenDataAccessImplementation<ActivationToken>(){
        @Override
        public String getTypeEntity() {
            return ActivationToken.class.getSimpleName();
        }
        
    }), //
    
    AUTHENTIFICATIONTOKEN(new AuthenticationTokenServiceImplementation(), AuthenticationDataAccessImplementation.INSTANCE), //
    ;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private TypeToken(TokenService service, TokenDataAccess dao){
        this.service = service;
        this.dao = dao;
    }
    
    private final TokenService<? extends TokenDataAccess<? extends Token>> service;
    
    private final TokenDataAccess<? extends TokenDataAccess<? extends Token>> dao;
}
