package fr.nimrod.info.exception.security;

import fr.nimrod.info.exception.GedDictException;
import fr.nimrod.info.exception.GedException;

public class GedSecurityActivationTokenExpiredException extends GedException {

    private static final long serialVersionUID = 1L;

    public GedSecurityActivationTokenExpiredException() {
        this.setCodeRetour(GedDictException.ACTIVATIONTOKENEXPIRED.getCodeRetour());
        this.setMessage(GedDictException.ACTIVATIONTOKENEXPIRED.getMessage());
    }
}
