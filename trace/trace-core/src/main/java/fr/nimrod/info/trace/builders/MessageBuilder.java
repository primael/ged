package fr.nimrod.info.trace.builders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.nimrod.info.trace.operations.ElementOperation;
import fr.nimrod.info.trace.validator.ElementValidator;
import fr.nimrod.info.trace.validator.common.IntegerValidator;
import fr.nimrod.info.trace.validator.common.TimeStampValidator;

public abstract class MessageBuilder {

	/**
	 * FIXME Voir l'implementation
	 */
	private Map<String, String> elements = new HashMap<String, String>();

	public MessageBuilder() {
		with(CompulsorySecurityOperations.TYPETRACE, getTypeTrace());
		with(CompulsorySecurityOperations.TIMESTAMP, LocalDateTime.now());
	}

	public MessageBuilder with(ElementOperation elementOperation,
			Object... values) {
		checkKey(values[0], elementOperation);

		String temporaryValue = elementOperation.getElementValidator()
				.validateAndTransform(values);
		elements.put(elementOperation.getCle(),
				elementOperation.getValeur(temporaryValue).toString());

		return this;
	}

	public String getMessage() {
		return this.generateMessage(' ');
	}
	
	abstract protected int getTypeTrace();

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

	private StringBuilder generateOutputForElement(List<String> entries,
			char separator) {
		StringBuilder outputString = new StringBuilder();

		entries.stream(). //
				forEach(entry -> outputString.append(entry + "="
						+ elements.get(entry) + separator));

		return outputString;
	}

	private enum CompulsorySecurityOperations implements ElementOperation {

		TYPETRACE("type_trace", IntegerValidator.INSTANCE),
		TIMESTAMP("date_creation", TimeStampValidator.INSTANCE);

		private final String cle;
		private final ElementValidator<?> elementValidator;

		private CompulsorySecurityOperations(String cle,
				ElementValidator<?> elementValidator) {
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

}
