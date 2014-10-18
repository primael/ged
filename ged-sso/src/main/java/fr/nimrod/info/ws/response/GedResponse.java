package fr.nimrod.info.ws.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;
import fr.nimrod.info.exception.GedException;

@XmlRootElement
public abstract class GedResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int codeRetour = 0;

    @Getter
    @Setter
    private String message = "OK";

    public void addException(GedException exception) {
        this.setCodeRetour(exception.getCodeRetour());
        this.setMessage(exception.getMessage());
    }
}
