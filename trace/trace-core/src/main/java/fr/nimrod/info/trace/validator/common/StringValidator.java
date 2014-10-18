package fr.nimrod.info.trace.validator.common;

import java.util.Base64;

import lombok.SneakyThrows;
import fr.nimrod.info.trace.validator.ElementValidator;

public class StringValidator implements ElementValidator<String> {

	@Override
	public String validateAndTransform(Object... object) {
		if (object != null && object[0]!=null && object.length == 1 && object[0] instanceof String) {
			return transform((String) object[0]);
		}
		return "*absent*";
	}

	@Override
	public String logToObject(String valeur) {
		return String.valueOf(valeur);
	}
	
	@SneakyThrows
	private String transform(String string){
		String toReturn = "\"" + string + "\"";
		
		if(string.matches(".*([^\\w\\s\\d\\p{Punct}]|[\"<>])+.*")){
			//Encoding en base64
			toReturn = "<" + Base64.getEncoder().encodeToString(string.getBytes("utf-8")) + ">";
		}
		
		return toReturn;
	}
}
