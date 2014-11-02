package fr.nimrod.info.model.compte;

import fr.nimrod.info.model.User;

public class CompteInactif implements StatutUserEtat {

    private static final long serialVersionUID = 1L;

    private User utilisateur;
    
    public CompteInactif(User user) {
        this.utilisateur = user;
    }

    @Override
    public void activerCompte() {
        this.utilisateur.setStatut(new CompteActif(utilisateur));
    }
}
