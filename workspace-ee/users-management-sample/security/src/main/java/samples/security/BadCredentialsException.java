/**
 * 
 */
package samples.security;

/**
 * @author PSLPT 147
 *
 */
public class BadCredentialsException extends AuthenticationException {

	public BadCredentialsException(String message) {
		super(message);
	}

}
