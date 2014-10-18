package fr.nimrod.info.model.authenticate;

public interface StatutAuthenticate {

    /**
     * Méthode permettant d'invalider un jeton d'authentification.
     */
    default void invalidate() {
        throw new IllegalStateException("Le jeton ne peut pas être invalidé, car il n'est pas valide.");
    }

    /**
     * Méthode permettant de valider un jeton d'authentication.
     */
    default void validate() {
        throw new IllegalStateException("Le jeton n'est plus valide.");
    }
}
