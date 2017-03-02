package com.progressoft.jip.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.progressoft.jip.factorypool.FactoryPool;
import com.progressoft.jip.factorypool.ObjectFactory;

public class DataSourceImple implements DataSource, ObjectFactory<Connection> {

	private FactoryPool<Connection> pool = new FactoryPool<Connection>(2, this);
	private String username;
	private String password;
	private String url;
	private String driverClass;

	public DataSourceImple(JDBCSettings settings) {
		this.username = settings.getUsername();
		this.password = settings.getPassword();
		this.url = settings.getUrl();
		this.driverClass = settings.getDriverClass();
	}

	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	public void setLoginTimeout(int seconds) throws SQLException {

	}

	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	public Connection getConnection() throws SQLException {
		Connection connection = pool.take();
		return connection;
	}

	public Connection getConnection(String username, String password) throws SQLException {
		Connection connection = pool.take();
		return connection;
	}

	public Connection newInstance() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO enhancement, hiding
			throw new IllegalStateException("uable to create connection", e);
		}
	}

}
