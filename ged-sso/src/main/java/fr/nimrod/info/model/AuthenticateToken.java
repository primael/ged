package fr.nimrod.info.model;

import java.time.LocalDateTime;
import java.util.UUID;

import fr.nimrod.info.model.authenticate.StatutAuthenticate;
import lombok.Getter;
import lombok.Setter;

/**
 * Un jeton d'authentification est caracterise par: - un identifiant - un timestamp de creation - un utilisateur a
 * l'origine de la demande
 * 
 * @author pbruant-ext
 *
 */
@Getter
public class AuthenticateToken {

    private UUID token = UUID.randomUUID();

    @Setter
    private LocalDateTime timeStamp = LocalDateTime.now();

    private User user;

    @Setter
    private StatutAuthenticate statut;

    public AuthenticateToken(User user) {
        this.user = user;
    }
}
