package fr.nimrod.info.dao;

import fr.nimrod.info.model.Token;
import fr.nimrod.info.model.User;
import fr.nimrod.info.model.token.TypeToken;

public interface TokenDataAccess<T extends Token> extends DataAccessObject<Long, T> {

    Token findTokenByToken(String token);
    
    default void invalidatePreviousTokens(User utilisateur){
        //N'est pas nécessaire pour toutes les DAOs..
        //Quoique (généralisable?)
    }
    
    static TokenDataAccess<?> getDataAccess(TypeToken typeToken) {
        return typeToken.getDao();
    }

//    default String getTypeEntity() {
//        return Token.class.getSimpleName();
//    }
}
