/**
 * 
 */
package fr.nimrod.info.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author x105723
 *
 */
@AllArgsConstructor
@NoArgsConstructor
public class User {

	
	@Getter
	@Setter
	private String login;
	
	@Getter
	@Setter
	private String email;
	
	@Getter
	@Setter
	private transient char[] password;

	@Getter
	@Setter
	private transient String salt;
	
	@Getter
	@Setter
	private transient String hash;
}
