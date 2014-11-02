/**
 * 
 */
package fr.nimrod.info.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import fr.nimrod.info.dao.converter.PhoneNumberConverter;
import fr.nimrod.info.model.compte.CompteInactif;
import fr.nimrod.info.model.compte.StatutUserEtat;

/**
 * @author x105723
 *
 */
@NoArgsConstructor
@Entity
@Table(name = "utilisateur")
@ToString
@EqualsAndHashCode
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public User(String login, String email, char[] password, String salt, String hash) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.hash = hash;
    }

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long identifiant;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String login;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Getter
    @Setter
    @Transient
    private transient char[] password;

    @Getter
    @Setter
    @Column(nullable = false)
    private String salt;

    @Getter
    @Setter
    @Column(nullable = false)
    private String hash;

    @Getter
    @Setter
    @Transient
    private StatutUserEtat statut = new CompteInactif(this);

    @Getter
    @Setter
    private boolean actif = false;

    @Getter
    @Setter
    @Column(name = "nbrEssai", nullable = false)
    private int nbrEssai = 0;
    
    @Getter
    @Setter
    @Convert(converter = PhoneNumberConverter.class)
    private PhoneNumber numeroTelephone;
}
