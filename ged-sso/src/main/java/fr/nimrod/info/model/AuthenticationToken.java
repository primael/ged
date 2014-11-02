package fr.nimrod.info.model;

import lombok.NoArgsConstructor;

/**
 * Un jeton d'authentification est caracterise par: - un identifiant - un timestamp de creation - un utilisateur a
 * l'origine de la demande
 * 
 * @author pbruant-ext
 *
 */
@NoArgsConstructor
public class AuthenticationToken extends Token {

    private static final long serialVersionUID = 1L;

    public AuthenticationToken(User utilisateur) {
        super(utilisateur);
    }

}

