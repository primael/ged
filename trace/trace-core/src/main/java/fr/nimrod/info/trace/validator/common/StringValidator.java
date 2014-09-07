package fr.nimrod.info.trace.validator.common;

import fr.nimrod.info.trace.validator.ElementValidator;

public enum StringValidator implements ElementValidator<String> {

	INSTANCE;

	@Override
	public String validateAndTransform(Object... object) {
		if (object[0] != null && object.length == 1 && object[0] instanceof String) {
			return (String) object[0];
		}
		return "";
	}

	@Override
	public String logToObject(String valeur) {
		return valeur;
	}
	
	
}
