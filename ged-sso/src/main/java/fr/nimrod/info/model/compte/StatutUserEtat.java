package fr.nimrod.info.model.compte;

import java.io.Serializable;

public interface StatutUserEtat extends Serializable {

    default void ajouterEssai() {
        throw new IllegalStateException("Un compte inactif ne peut pas d'essai de validation");
    }

    default void validerEssai() {
        throw new IllegalStateException("Un compte inactif ne peut pas d'essai de validation");
    }

    default void activerCompte() {
        throw new IllegalStateException("Un compte inactif ne peut pas d'essai de validation");
    }

}
