/**
 * 
 */
package login;

/**
 * @author PSLPT 147
 *
 */
public class BadCredentialsException extends AuthenticationException {

	public BadCredentialsException(String message) {
		super(message);
	}

}
