package fr.nimrod.info.model.compte;

public interface StatutUserEtat {

    default void ajouterEssai() {
        throw new IllegalArgumentException("Un compte inactif ne peut pas d'essai de validation");
    }

    default void validerEssai() {
        throw new IllegalArgumentException("Un compte inactif ne peut pas d'essai de validation");
    }

    default void activerCompte() {
        throw new IllegalArgumentException("Un compte inactif ne peut pas d'essai de validation");
    }

}
