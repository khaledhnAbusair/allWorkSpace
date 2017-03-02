package login;

public class SecurityContext {
	private static ThreadLocal<SecurityContext> local = new ThreadLocal<>();

	private UserInfo userInfo;

	public static void setSecurityContext(SecurityContext context) {
		local.set(context);
	}

	public static SecurityContext getCurrentContext() {
		return local.get();
	}

	public static void clear() {
		local.remove();
	}

	public boolean isAnonymous() {
		return userInfo == null;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

}
