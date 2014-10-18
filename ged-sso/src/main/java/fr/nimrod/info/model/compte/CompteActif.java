package fr.nimrod.info.model.compte;

import fr.nimrod.info.model.User;

public class CompteActif implements StatutUserEtat {

    private User user;

    public CompteActif(User user) {
        if (null == user) {
            throw new IllegalArgumentException("Un etat d'un compte est necessairement lie a un utilisateur");
        }
        this.user = user;
    }

    @Override
    public void ajouterEssai() {
        this.user.setNbrEssai(this.user.getNbrEssai() + 1);
        if (this.user.getNbrEssai() > 5) {
            this.user.setStatut(new CompteBloquer());
        }
    }

    @Override
    public void validerEssai() {

    }
}
