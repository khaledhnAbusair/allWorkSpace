/**
 * 
 */
package samples.security;

/**
 * @author phi01tech
 *
 */
public interface UsersManager {

	public void addUser(UserInfo userInfo, String password);

	public void updateUser(UserInfo userInfo);

	public void changePassword(String username, String password, String newPassword) throws BadCredentialsException;

	public Iterable<UserInfo> listAllUsers();
}
