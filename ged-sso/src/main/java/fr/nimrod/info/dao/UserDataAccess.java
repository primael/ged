package fr.nimrod.info.dao;

import fr.nimrod.info.dao.impl.UserDataAccessImplementation;
import fr.nimrod.info.model.User;

public interface UserDataAccess extends DataAccessObject<Long, User> {

    User findUserByLogin(String login);

    User findUserByMail(String mail);
    
    static UserDataAccess getDataAccess() {
        return UserDataAccessImplementation.INSTANCE;
    }

    default String getTypeEntity() {
        return User.class.getSimpleName();
    }

}
