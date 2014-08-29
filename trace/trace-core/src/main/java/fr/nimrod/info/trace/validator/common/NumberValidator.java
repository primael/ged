package fr.nimrod.info.trace.validator.common;

import fr.nimrod.info.trace.validator.ElementValidator;

public enum NumberValidator implements ElementValidator<Number> {

	INSTANCE;

	@Override
	public String validateAndTransform(Object... object) {
		if (object[0] != null && object.length == 1 && object[0] instanceof Number) {
			return ((Number)object[0]).toString();
		}
		return "";
	}

	@Override
	public Number logToObject(String valeur) {
		return null;
	}
	
}
