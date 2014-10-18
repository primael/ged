package fr.nimrod.info.ws.response.security.impl;

import lombok.Getter;
import lombok.Setter;
import fr.nimrod.info.ws.response.security.GedSecurityResponse;

public class GedSecurityAuthenticationResponse extends GedSecurityResponse {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String token;
}
