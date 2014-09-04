package fr.nimrod.info.exception;

import lombok.Getter;
import lombok.Setter;

public abstract class GedException extends Exception {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int codeRetour;
	
	@Getter
	@Setter
	private String message;
}
