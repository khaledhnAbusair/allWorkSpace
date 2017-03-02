package security;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserProfile implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private LocalDateTime loginTime;

	public UserProfile(String username, LocalDateTime loginTime) {
		this.username = username;
		this.loginTime = loginTime;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public String getUsername() {
		return username;
	}

}
