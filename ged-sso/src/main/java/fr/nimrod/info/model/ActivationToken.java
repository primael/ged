package fr.nimrod.info.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("activation")
public class ActivationToken extends Token {

    private static final long serialVersionUID = 1L;

    public ActivationToken(User utilisateur) {
        super(utilisateur);
    }

}
