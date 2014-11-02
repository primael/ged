package fr.nimrod.info.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.model.AuthenticationToken;
import fr.nimrod.info.model.User;

public class AuthenticationDataAccessImplementation extends TokenDataAccessImplementation<AuthenticationToken> {

    public static final TokenDataAccessImplementation<AuthenticationToken> INSTANCE = new AuthenticationDataAccessImplementation();
    
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
    private Map<User, List<AuthenticationToken>> users = dataBaseUser.getHashMap("users");
    
    //Carte des jetons
    private Map<String, AuthenticationToken> tokens = dataBaseUser.getHashMap("tokens");

    private AuthenticationDataAccessImplementation() {
    }
    
    @Override
    public String getTypeEntity() {
        return AuthenticationToken.class.getSimpleName();
    }

    @Override
    public AuthenticationToken findTokenByToken(String token) {
        return tokens.get(token);
    }
    
    @Override
    public void invalidatePreviousTokens(User utilisateur){
        //On récupère la liste des tokens pour l'utilisateur
        List<AuthenticationToken> tokens = users.get(utilisateur);
        if( tokens!=null ){
            for(AuthenticationToken token : tokens){
                token.setValid(false);
            }
            
            dataBaseUser.commit();
        }
    }
    
    public AuthenticationToken createEntity(AuthenticationToken entity) throws GedException {
       
        //Inscription dans la carte utilisateurs
        addAuthenticationTokenToTokens(entity);
        
        //Inscription dans la carte tokens
        tokens.put(entity.getToken(), entity);
        
        dataBaseUser.commit();
        
       return entity;
    }
    
    private void addAuthenticationTokenToTokens(AuthenticationToken token) {
        if(users.get(token.getUtilisateur()) == null) {
            users.put(token.getUtilisateur(), new ArrayList<AuthenticationToken>());
        }
        
        users.get(token.getUtilisateur()).add(token);
    }
    

    
}
