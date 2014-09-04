package fr.nimrod.info.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
public enum GedDictException {

	AUTHENTICATIONFAILED(100, "Erreur dans le couple login/mot de passe.")
	;
	
	@Getter
	private int codeRetour;
	
	@Getter
	private String message;
}
