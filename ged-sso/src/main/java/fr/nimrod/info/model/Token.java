package fr.nimrod.info.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import fr.nimrod.info.dao.converter.LocalDateTimeConverter;

@Entity
@NoArgsConstructor
@Table(name = "token")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_TOKEN")
@ToString
@EqualsAndHashCode
public abstract class Token implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long identifiant;

    @Getter
    @Column(nullable = false)
    private final String token = UUID.randomUUID().toString();

    @Getter
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(nullable = false)
    private final LocalDateTime timeStamp = LocalDateTime.now();

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(nullable = false)
    private User utilisateur;

    @Getter
    @Setter
    @JoinColumn(nullable = false)
    private boolean valid = true;
    
    public Token(User utilisateur) {
        this.utilisateur = utilisateur;
    }
}
