package fr.nimrod.info.exception.security;

import fr.nimrod.info.exception.GedDictException;
import fr.nimrod.info.exception.GedException;

public class GedSecurityActionNotAllowedException extends GedException {
	
	private static final long serialVersionUID = 1L;

	public GedSecurityActionNotAllowedException() {
		this.setCodeRetour(GedDictException.ACTIONNOTALLOWED.getCodeRetour());
		this.setMessage(GedDictException.ACTIONNOTALLOWED.getMessage());
	}
}
