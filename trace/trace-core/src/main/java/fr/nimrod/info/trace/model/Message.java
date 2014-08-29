package fr.nimrod.info.trace.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

public abstract class Message {

	@Getter
	@Setter
	private LocalDateTime timestamp;
	
	@Getter
	@Setter
	private int typeTrace;
	
}
