package fr.nimrod.info.exception.security;

import fr.nimrod.info.exception.GedDictException;
import fr.nimrod.info.exception.GedException;

public class GedSecurityActivationTokenNotExistException extends GedException {

    private static final long serialVersionUID = 1L;

    public GedSecurityActivationTokenNotExistException() {
        this.setCodeRetour(GedDictException.ACTIVATIONTOKENNOTEXIST.getCodeRetour());
        this.setMessage(GedDictException.ACTIVATIONTOKENNOTEXIST.getMessage());
    }
}
