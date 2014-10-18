package fr.nimrod.info.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import fr.nimrod.info.dao.AuthenticationDataAccess;
import fr.nimrod.info.model.AuthenticateToken;
import fr.nimrod.info.model.User;

public enum AuthenticationDataAccessImplementation implements AuthenticationDataAccess {

    INSTANCE;

    private File dbFile;

    {
        try {
            dbFile = File.createTempFile("mapdb", "temp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DB dataBaseUser = DBMaker //
            .newFileDB(dbFile) //
            .closeOnJvmShutdown() //
            .make();

    // Carte des utilisateurs connectes
    private Map<User, AuthenticateToken> users = dataBaseUser.getHashMap("users");

    @Override
    public void invalidateTokenFor(User user) {
        AuthenticateToken token = users.get(user);

        if (token != null) {

        }
    }

    @Override
    public void persistToken(AuthenticateToken token) {

    }

}
