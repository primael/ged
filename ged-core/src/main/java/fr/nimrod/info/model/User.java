/**
 * 
 */
package fr.nimrod.info.model;

import javax.persistence.Column;
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

/**
 * @author x105723
 *
 */
@NoArgsConstructor
@Entity
@Table(name="utilisateur")
@ToString
@EqualsAndHashCode
public class User {

	public User(String login, String email, char[] password, String salt,
			String hash) {
		this.login = login;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.hash = hash;
	}

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long identifiant;
	
	@Getter
	@Setter
	@Column(nullable=false, unique=true)
	private String login;
	
	@Getter
	@Setter
	@Column(nullable=false, unique=true)
	private String email;
	
	@Getter
	@Setter
	@Transient
	private transient char[] password;

	@Getter
	@Setter
	@Column(nullable=false)
	private String salt;
	
	@Getter
	@Setter
	@Column(nullable=false)
	private String hash;
}
