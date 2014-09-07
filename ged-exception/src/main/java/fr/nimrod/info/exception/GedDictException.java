package fr.nimrod.info.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
public enum GedDictException {

	AUTHENTICATIONFAILED(100, "Erreur dans le couple login/mot de passe."),
	ACTIONNOTALLOWED(101, "Vous n'êtes pas autorisé à executer cette action."),
	
	TECHNICAL(200, "Erreur technique survenue. Contactez l'administrateur.")
	;
	
	@Getter
	private int codeRetour;
	
	@Getter
	private String message;
}
