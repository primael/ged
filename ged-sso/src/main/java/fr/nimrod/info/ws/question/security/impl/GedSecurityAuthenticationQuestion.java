package fr.nimrod.info.ws.question.security.impl;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;
import fr.nimrod.info.ws.question.security.GedSecurityQuestion;

@XmlRootElement(name = "authentication")
public class GedSecurityAuthenticationQuestion implements GedSecurityQuestion {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String password;
}
