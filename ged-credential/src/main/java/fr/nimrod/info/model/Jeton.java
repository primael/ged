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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import fr.nimrod.info.dao.converters.ConverterLocalDateTime;

/**
 * Un jeton est généré (unicité) lors de l'authentification d'un ultilisateur.
 * Le jeton est un moyen permettant d'identifier et de tracer les actions d'un 
 * utilisateur durant sa session.

 * @author Bruant
 *
 */
@ToString
@NoArgsConstructor
@Entity
public class Jeton {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long identifiant;
	
	@Getter
	@Column(unique=true, nullable=false)
	private UUID jeton = UUID.randomUUID();
	
	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name="identifiant_utilisateur", nullable=false)
	private User utilisateur;
	
	@Getter
	@Setter
	@Column(nullable=false)
	private boolean actif = true;
	
	@Getter
	@Convert(converter=ConverterLocalDateTime.class)
	private LocalDateTime dateCreate = LocalDateTime.now();
	
	public Jeton(User utilisateur) {
		this.utilisateur = utilisateur;
	}
	
}
