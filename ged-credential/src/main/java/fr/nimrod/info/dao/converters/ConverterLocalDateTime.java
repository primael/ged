package fr.nimrod.info.dao.converters;

import java.time.LocalDateTime;
import java.sql.Timestamp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class ConverterLocalDateTime implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
		return Timestamp.valueOf(entityValue);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp databaseValue) {
		return databaseValue.toLocalDateTime();
	}

}
