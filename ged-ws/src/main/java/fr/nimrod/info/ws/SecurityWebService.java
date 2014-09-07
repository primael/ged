package fr.nimrod.info.ws;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.service.UserService;
import fr.nimrod.info.ws.question.security.impl.GedSecurityAuthenticationQuestion;
import fr.nimrod.info.ws.response.GedResponse;
import fr.nimrod.info.ws.response.security.impl.GedCreateAuthenticationResponse;
import fr.nimrod.info.ws.response.security.impl.GedSecurityAuthenticationResponse;

@WebService
@SOAPBinding(style = Style.RPC)
public class SecurityWebService {

	private UserService service = UserService.getService();

	public GedResponse authentication(
			@WebParam GedSecurityAuthenticationQuestion question) {

		GedResponse response = new GedSecurityAuthenticationResponse();
		try {
			service.authenticate(question.getLogin(), question.getPassword());
		} catch (GedException exception) {
			response.addException(exception);
		}
		
		return response;
	}

	public GedResponse createUser(@WebParam String login, @WebParam String mail,
			@WebParam String password) {

		GedResponse response = new GedCreateAuthenticationResponse();
		
		try {
			service.createUser(login, mail, password);
		} catch (GedException exception) {
			response.addException(exception);
		}
		
		return response;
	}
}
