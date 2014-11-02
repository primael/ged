package fr.nimrod.info.exception.security;

import fr.nimrod.info.exception.GedDictException;
import fr.nimrod.info.exception.GedException;

public class GedSecurityAuthenticationAttributeAllReadyExistsException extends GedException {

    private static final long serialVersionUID = 1L;

    public GedSecurityAuthenticationAttributeAllReadyExistsException(String attribute) {
        this.setCodeRetour(GedDictException.AUTHENTICATIONATTRIBUTEALLREADYEXISTS.getCodeRetour());
        this.setMessage(GedDictException.AUTHENTICATIONATTRIBUTEALLREADYEXISTS.getMessage().replaceAll("%attribute%", attribute));
    }
}
