package fr.nimrod.info.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
public enum GedDictException {

	AUTHENTICATIONFAILED(100, "Erreur dans le couple login/mot de passe."), //
	ACTIONNOTALLOWED(101, "Vous n'êtes pas autorisé à executer cette action."), //
	ACTIVATIONTOKENNOTEXIST(102, "Le jeton d'activation fourni n'existe pas."), //
	ACTIVATIONTOKENEXPIRED(103, "Le jeton d'activation n'est plus valable."), //
    ACTIVATIONTOKENALLREADYUSED(104, "Le jeton a déjà été joué."), //
    
	AUTHENTICATIONATTRIBUTEALLREADYEXISTS(110, "L'element fournit existe déjà. [%attribute%]"), //
	
	TECHNICAL(200, "Erreur technique survenue. Contactez l'administrateur."), //
	;
	
	@Getter
	private int codeRetour;
	
	@Getter
	private String message;
}
