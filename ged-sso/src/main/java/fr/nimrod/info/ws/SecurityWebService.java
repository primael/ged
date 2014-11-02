package fr.nimrod.info.ws;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import fr.nimrod.info.exception.GedException;
import fr.nimrod.info.model.Token;
import fr.nimrod.info.model.User;
import fr.nimrod.info.model.token.TypeToken;
import fr.nimrod.info.service.TokenService;
import fr.nimrod.info.service.UserService;
import fr.nimrod.info.ws.question.security.impl.GedSecurityAuthenticationQuestion;
import fr.nimrod.info.ws.response.GedResponse;
import fr.nimrod.info.ws.response.security.impl.GedCreateAuthenticationResponse;
import fr.nimrod.info.ws.response.security.impl.GedSecurityAuthenticationResponse;

@WebService(
        portName="SecurityPort",
        serviceName="Security")
@SOAPBinding(style = Style.RPC)
public class SecurityWebService {

    private UserService userService = UserService.getService();
    
    @SuppressWarnings("unchecked")
    private TokenService<Token> activationTokenService = (TokenService<Token>) TokenService.getService(TypeToken.ACTIVATIONTOKEN);
    
    @SuppressWarnings("unchecked")
    private TokenService<Token> authenticationTokenService = (TokenService<Token>) TokenService.getService(TypeToken.AUTHENTIFICATIONTOKEN);

    @Resource
    private WebServiceContext context;
    
    public GedSecurityAuthenticationResponse authentication(@WebParam GedSecurityAuthenticationQuestion question) {

        GedSecurityAuthenticationResponse response = new GedSecurityAuthenticationResponse();
        try {
            
            this.getOrigine();
            
            userService.authenticate(question.getLogin(), question.getPassword());
            // Creation d'un jeton d'authentification
            response.setToken(authenticationTokenService.createToken(userService.getUserByLogin(question.getLogin())).getToken());

        } catch (GedException exception) {
            response.addException(exception);
        }

        return response;
    }

    public GedResponse createUser(@WebParam(name="identifiant") String login, @WebParam(name="courriel") String mail, @WebParam(name="motDePasse") String password) {

        GedResponse response = new GedCreateAuthenticationResponse();

        try {
            User utilisateur = userService.createUser(login, mail, password);
            activationTokenService.createToken(utilisateur);
        } catch (GedException exception) {
            response.addException(exception);
        }

        return response;
    }
    
    public GedResponse activateUser(@WebParam(name="activationToken") String token){
        
        GedResponse response = new GedSecurityAuthenticationResponse();
        
        try {
            activationTokenService.validateToken(token);
        } catch (GedException exception) {
          response.addException(exception);
        }
        return response;
    }
    
    private void getOrigine(){
        MessageContext messageContext = context.getMessageContext();
        System.out.println(messageContext.get(MessageContext.SERVLET_CONTEXT));
        //System.out.println(request.getRemoteAddr());
    }
}
