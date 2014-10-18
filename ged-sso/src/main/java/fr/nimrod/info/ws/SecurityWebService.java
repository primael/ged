package fr.nimrod.info.ws;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.service.AuthenticationTokenService;
import fr.nimrod.info.service.UserService;
import fr.nimrod.info.ws.question.security.impl.GedSecurityAuthenticationQuestion;
import fr.nimrod.info.ws.response.GedResponse;
import fr.nimrod.info.ws.response.security.impl.GedCreateAuthenticationResponse;
import fr.nimrod.info.ws.response.security.impl.GedSecurityAuthenticationResponse;

@WebService
@SOAPBinding(style = Style.RPC)
public class SecurityWebService {

    private UserService userService = UserService.getService();
    private AuthenticationTokenService authenticationTokenService = AuthenticationTokenService.getService();

    public GedResponse authentication(@WebParam GedSecurityAuthenticationQuestion question) {

        GedSecurityAuthenticationResponse response = new GedSecurityAuthenticationResponse();
        try {
            userService.authenticate(question.getLogin(), question.getPassword());
            // Creation d'un jeton d'authentification
            response.setToken(authenticationTokenService.createAuthenticateToken(userService.getUserByLogin(question
                    .getLogin())));

        } catch (GedException exception) {
            response.addException(exception);
        }

        return response;
    }

    public GedResponse createUser(@WebParam(name="identifiant") String login, @WebParam(name="courriel") String mail, @WebParam(name="motDePasse") String password) {

        GedResponse response = new GedCreateAuthenticationResponse();

        try {
            userService.createUser(login, mail, password);
        } catch (GedException exception) {
            response.addException(exception);
        }

        return response;
    }
}
