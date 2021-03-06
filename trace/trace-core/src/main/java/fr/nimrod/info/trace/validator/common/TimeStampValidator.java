package fr.nimrod.info.trace.validator.common;

import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;

import fr.nimrod.info.trace.validator.ElementValidator;

public interface TimeStampValidator extends ElementValidator<ChronoLocalDateTime<?>> {

	@Override
	default String validateAndTransform(Object... object) {
		String toReturn = null;
		if (object == null || object[0] == null) {
			toReturn = "*absent*";
		} else if (!(object[0] instanceof ChronoLocalDateTime<?>) || object.length != 1) {
			throw new IllegalArgumentException("L'objet " + object[0].getClass() + " n'est pas une chaine de type Date");
		} else {
			toReturn = "[" + ((ChronoLocalDateTime<?>) object[0]).format(getDateTimeFormatter()) + "]";
		}

		return toReturn;
	}


	@Override
	default ChronoLocalDateTime<?> logToObject(String valeur) {
		return (ChronoLocalDateTime<?>) getDateTimeFormatter().parse(valeur);
	}

	default DateTimeFormatter getDateTimeFormatter() {
		return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
	}
}
