package fr.nimrod.info.exception.security;

import fr.nimrod.info.exception.GedDictException;
import fr.nimrod.info.exception.GedException;

public class GedSecurityAuthenticationFailedException extends GedException{

	private static final long serialVersionUID = 1L;

	public GedSecurityAuthenticationFailedException() {
		this.setCodeRetour(GedDictException.AUTHENTICATIONFAILED.getCodeRetour());
		this.setMessage(GedDictException.AUTHENTICATIONFAILED.getMessage());
	}
}
