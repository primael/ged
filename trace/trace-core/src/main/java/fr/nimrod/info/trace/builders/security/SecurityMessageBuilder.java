package fr.nimrod.info.trace.builders.security;

import fr.nimrod.info.trace.builders.MessageBuilder;
import fr.nimrod.info.trace.operations.ElementOperation;
import fr.nimrod.info.trace.validator.ElementValidator;
import fr.nimrod.info.trace.validator.common.NumberValidator;

public class SecurityMessageBuilder extends MessageBuilder {

	public SecurityMessageBuilder() {
		with(CompulsorySecurityOperations.TYPETRACE, 100);
	}
	
	private enum CompulsorySecurityOperations implements ElementOperation {
		
		TYPETRACE("type_trace", NumberValidator.INSTANCE)
		;

		private final String cle;
		private final ElementValidator<?> elementValidator;
		
		private CompulsorySecurityOperations(String cle, ElementValidator<?> elementValidator) {
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
