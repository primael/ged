package fr.nimrod.info.trace.evenement;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SecurityEvent {

	TRY_AUTHENTICATION(101),
	AUTHENTICATION_SUCCESS(102),
	AUTHENTICATION_FAILED(103),
	USER_BLOCKED(104),
	USER_INACTIVE(105),
	TOKEN_CREATION(106),
	
	;
	
	@Getter
	private final int typeEvent;
	
	public static SecurityEvent getSecurityEvent(int typeEvent){
		
		for(SecurityEvent event : SecurityEvent.values()){
			if(event.getTypeEvent() == typeEvent){
				return event;
			}
		}
		return null;
	}
}
