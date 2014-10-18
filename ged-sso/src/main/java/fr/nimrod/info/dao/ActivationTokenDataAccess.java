package fr.nimrod.info.dao;

import fr.nimrod.info.dao.impl.ActivationTokenDataAccessImplementation;
import fr.nimrod.info.model.ActivationToken;
public interface ActivationTokenDataAccess extends DataAccessObject<Long, ActivationToken> {

    ActivationToken findActivationTokenByToken(String token);
    
    static ActivationTokenDataAccess getDataAccess() {
        return ActivationTokenDataAccessImplementation.INSTANCE;
    }

    default String getTypeEntity() {
        return ActivationToken.class.getSimpleName();
    }
}
