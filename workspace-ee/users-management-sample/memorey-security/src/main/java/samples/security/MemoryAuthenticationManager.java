package samples.security;

import java.util.HashMap;
import java.util.Map;

public class MemoryAuthenticationManager implements AuthenticationManager {

	private Map<String, Credentials> credentials = new HashMap<>();

	{
		Credentials user = new Credentials();
		user.username = "admin";
		user.fullName = "Administrator";
		user.password = "pass1234";
		credentials.put("admin", user);
	}

	@Override
	public UserInfo authenticate(AuthenticateRequest request) throws AuthenticationException {
		String username = request.getUsername();
		if (username != null && credentials.containsKey(username)) {
			Credentials info = credentials.get(username);
			if (info.password.equals(request.getPassword())) {
				return info.getUserInfo();
			}
		}
		throw new BadCredentialsException("Bad credentials");
	}

	private class Credentials {
		private String username;
		private String fullName;
		private String password;

		UserInfo getUserInfo() {
			return new UserInfo() {

				@Override
				public String getUsername() {
					return username;
				}

				@Override
				public String getFullName() {
					return fullName;
				}
			};
		}
	}
}
