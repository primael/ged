package fr.nimrod.info.trace.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.nimrod.info.trace.operations.ElementOperation;

public abstract class MessageBuilder {

	/**
	 * FIXME Voir l'implementation
	 */
	private Map<String, String> elements = new HashMap<String, String>();

	public MessageBuilder() {
	}
	
	public MessageBuilder with(ElementOperation elementOperation, Object... values) {
		checkKey(values[0], elementOperation);

		String temporaryValue = elementOperation.getElementValidator().validateAndTransform(values);
		elements.put(elementOperation.getCle(), elementOperation.getValeur(temporaryValue).toString());

		return this;
	}

	public String getMessage() {
		return this.generateMessage(' ');
	}

	private void checkKey(Object valeur, ElementOperation elementOperation) {
		if (this.elements.containsKey(elementOperation.getCle())) {
			if (valeur != null) {
				System.out.println("cle déjà présente");
			} else {
				System.out.println("cle déjà présente valeur = vide");
			}
		}
	}

	private String generateMessage(char separator) {
		List<String> entries = new ArrayList<String>(elements.keySet());
		StringBuilder outputString = new StringBuilder();
		outputString.append(generateOutputForElement(entries, separator));
		return outputString.toString();
	}

	private StringBuilder generateOutputForElement(List<String> entries, char separator) {
		StringBuilder outputString = new StringBuilder();

		entries.stream(). //
				forEach(entry -> outputString.append(entry + "=" + elements.get(entry) + separator));

		return outputString;
	}

}
