package com.progressoft.jip.datasource;

public class JDBCSettings {

	private String username;
	private String password;
	private String url;
	private String driverClass;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public static class Builder {
		private String username;
		private String password;
		private String url;
		private String driverClass;

		public void setUsername(String username) {
			this.username = username;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public void setDriverClass(String driverClass) {
			this.driverClass = driverClass;
		}

		public JDBCSettings build() {
			JDBCSettings jdbcSettings = new JDBCSettings();
			jdbcSettings.password = this.password;
			jdbcSettings.url = this.url;
			jdbcSettings.username = this.username;
			jdbcSettings.driverClass = this.driverClass;

			return jdbcSettings;
		}

	}

}
