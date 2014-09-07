package fr.nimrod.info.exception.technical;

import fr.nimrod.info.exception.GedDictException;
import fr.nimrod.info.exception.GedException;

public class GedTechnicalException extends GedException {

	private static final long serialVersionUID = 1L;

	public GedTechnicalException() {
		this.setCodeRetour(GedDictException.TECHNICAL.getCodeRetour());
		this.setMessage(GedDictException.TECHNICAL.getMessage());
	}
}
