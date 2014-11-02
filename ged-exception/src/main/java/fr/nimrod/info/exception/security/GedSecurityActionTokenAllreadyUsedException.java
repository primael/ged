package fr.nimrod.info.exception.security;

import fr.nimrod.info.exception.GedDictException;
import fr.nimrod.info.exception.GedException;

public class GedSecurityActionTokenAllreadyUsedException extends GedException {

    private static final long serialVersionUID = 1L;

    public GedSecurityActionTokenAllreadyUsedException() {
        this.setCodeRetour(GedDictException.ACTIVATIONTOKENALLREADYUSED.getCodeRetour());
        this.setMessage(GedDictException.ACTIVATIONTOKENALLREADYUSED.getMessage());
    }
}
