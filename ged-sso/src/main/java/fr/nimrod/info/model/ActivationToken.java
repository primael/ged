package fr.nimrod.info.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import fr.nimrod.info.dao.converter.LocalDateTimeConverter;

@Entity
@Table(name = "activation")
@ToString
@EqualsAndHashCode
public class ActivationToken {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long identifiant;
    
    @Getter
    @Column(nullable = false)
    private final String activationToken = UUID.randomUUID().toString();
    
    @Getter
    @Convert(converter=LocalDateTimeConverter.class)
    @Column(nullable = false)
    private final LocalDateTime timeStamp = LocalDateTime.now();
    
    @Getter
    @OneToOne
    @JoinColumn(nullable=false)
    private final User utilisateur;
    
    public ActivationToken(User utilisateur) {
        this.utilisateur = utilisateur;
    }
}
