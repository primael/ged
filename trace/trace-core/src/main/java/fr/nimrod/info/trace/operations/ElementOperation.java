package fr.nimrod.info.trace.operations;

import fr.nimrod.info.trace.validator.ElementValidator;

public interface ElementOperation {

	String getCle();

	default String getValeur(Object value){
		return value.toString();
	}

	ElementValidator<?> getElementValidator();
}
