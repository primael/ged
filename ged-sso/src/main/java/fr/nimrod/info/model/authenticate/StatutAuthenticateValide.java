package fr.nimrod.info.model.authenticate;

import java.time.LocalDateTime;

import fr.nimrod.info.model.AuthenticateToken;

public final class StatutAuthenticateValide implements StatutAuthenticate {

    private AuthenticateToken token;

    public StatutAuthenticateValide(AuthenticateToken token) {
        this.token = token;
    }

    @Override
    public void invalidate() {
        token.setStatut(new StatutAuthenticateInvalid());
    }

    @Override
    public void validate() {
        // Test de la durée de vie du token
        // FIXME Extraire la contrainte des 30 minutes.
        if (LocalDateTime.now().isAfter(token.getTimeStamp().plusMinutes(30l))) {
            token.setTimeStamp(LocalDateTime.now());
        } else {
            token.setStatut(new StatutAuthenticateInvalid());
            throw new IllegalStateException("La duree de vie du jeton est depasse.");
        }
    }

}
