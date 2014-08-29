package fr.nimrod.info.trace.validator;

public interface ElementValidator<T> {

	String validateAndTransform(Object... object);

	T logToObject(String valeur);
}
