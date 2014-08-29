package fr.nimrod.info.trace.validator.security;

import fr.nimrod.info.trace.evenement.SecurityEvent;
import fr.nimrod.info.trace.validator.ElementValidator;

public enum SecurityEventValidator implements ElementValidator<SecurityEvent> {

	INSTANCE;
	
	@Override
	public String validateAndTransform(Object... object) {
		String toReturn = null;
		if (object == null || object[0] == null) {
			toReturn = "*absent*";
		} else if (!(object[0] instanceof SecurityEvent) || object.length != 1) {
			throw new IllegalArgumentException("L'objet " + object[0].getClass() + " n'est pas une chaine de type SecurityEvent");
		} else {
			toReturn = String.valueOf(((SecurityEvent)object[0]).getTypeEvent());
		}
		return toReturn;
	}

	@Override
	public SecurityEvent logToObject(String valeur) {
		return null;
	}

}
