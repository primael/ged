package fr.nimrod.info.trace.model.security;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import fr.nimrod.info.trace.model.LoggingMessage;

/**
 * Une trace de securit� est une trace permettant de suivre les actions d'une session utilisateur.
 * La trace est compos�e comme suit:
 * <ul>
 * 	<li>un token de session. Identifiant de fa�on unique une session utilisateur.></li>
 * 	<li>un timestamp de cr�ation de trace.</li>
 * 	<li>un identifiant. Permettant d'identifier l'utilisateur.</li>
 * 	<li>une action. Permettant d'identifier l'action effectu�e.</li>
 * </ul>
 * @author x105723
 *
 */
public class SecurityMessage implements LoggingMessage {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private UUID token;
	
	@Getter
	@Setter
	private LocalDateTime timestamp;
	
	@Getter
	@Setter
	private int typeTrace;
		
	@Getter
	@Setter
	private String connectionFrom;
}
