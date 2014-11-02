package fr.nimrod.info.trace.operations.security;

import fr.nimrod.info.trace.operations.ElementOperation;
import fr.nimrod.info.trace.validator.ElementValidator;
import fr.nimrod.info.trace.validator.common.StringValidator;
import fr.nimrod.info.trace.validator.security.SecurityEventValidator;

public enum SecurityOperations implements ElementOperation{

	TOKEN("token", new StringValidator()),
	SECURITY_EVENT("security_event", SecurityEventValidator.INSTANCE),
	LOGIN("login", new StringValidator());
	
	private final String cle;
	private final ElementValidator<?> elementValidator;
	
	private SecurityOperations(String cle, ElementValidator<?> elementValidator) {
		this.cle = cle;
		this.elementValidator = elementValidator;
	}

	@Override
	public String getCle() {
		return this.cle;
	}

	@Override
	public ElementValidator<?> getElementValidator() {
		return this.elementValidator;
	}
}
