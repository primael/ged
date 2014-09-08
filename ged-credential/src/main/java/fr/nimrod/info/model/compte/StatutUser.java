package fr.nimrod.info.model.compte;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatutUser {

	COMPTEINACTIF(new CompteInactif()),
	COMPTEACTIF(new CompteActif()),
	COMPTEBLOQUER(new CompteBloquer())
	;
	
	private final StatutUserEtat etatCompte;
	
	public void ajouterEssai(){
		etatCompte.ajouterEssai();
	}
	
	public void validerEssai(){
		etatCompte.validerEssai();
	}
	
	public void activerCompte(){
		etatCompte.activerCompte();
	}
	
	
}
