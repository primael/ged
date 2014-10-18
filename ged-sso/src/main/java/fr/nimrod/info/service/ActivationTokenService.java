package fr.nimrod.info.service;

import fr.nimrod.info.model.ActivationToken;
import fr.nimrod.info.model.User;
import fr.nimrod.info.service.impl.ActivationTokenServiceImplementation;

/**
 * Service permettant la gestion des token d'activation
 * @author Primael Bruant
 *
 */
public interface ActivationTokenService {

    void validateActivationToken(ActivationToken activationToken);

    default ActivationToken createActivationToken(User utilisateur) {
        return new ActivationToken(utilisateur);
    }

    static ActivationTokenService getService() {
        return ActivationTokenServiceImplementation.INSTANCE;
    }

}
