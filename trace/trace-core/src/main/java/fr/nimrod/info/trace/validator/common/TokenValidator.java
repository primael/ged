package fr.nimrod.info.trace.validator.common;

import java.util.UUID;

import fr.nimrod.info.trace.validator.ElementValidator;

public enum TokenValidator implements ElementValidator<UUID> {

	INSTANCE;

	@Override
	public String validateAndTransform(Object... object) {
		String toReturn = null;
		if (object == null || object[0] == null) {
			toReturn = "*absent*";
		} else if (!(object[0] instanceof UUID) || object.length != 1) {
			throw new IllegalArgumentException("L'objet " + object[0].getClass() + " n'est pas une chaine de type UUID");
		} else {
			toReturn = object[0].toString();
		}
		return toReturn;
	}

	@Override
	public UUID logToObject(String valeur) {
		return UUID.fromString(valeur);
	}
	
	
}
