/**
 * 
 */
package com.progressoft.jip.datasource;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.progressoft.util.pool.FactoryBasedPool;
import com.progressoft.util.pool.ObjectFactory;

/**
 * @author PSLPT 147
 *
 */
public class DataSourceImpl implements DataSource, ObjectFactory<Connection> {

	private FactoryBasedPool<Connection> pool = new FactoryBasedPool<>(2, this);
	private String username;
	private String password;
	private String url;
	private String driverClass;

	public DataSourceImpl(String username, String password, String url, String driverClass) {
		super();
		this.username = username;
		this.password = password;
		this.url = url;
		this.driverClass = driverClass;
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = pool.take();
		// I shall capture when the connection.close is invoked
		// return new PooledConnection(connection, pool);
		ConnectionHandler handler = new ConnectionHandler();
		handler.connection = connection;

		return (Connection) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				new Class[] { Connection.class }, handler);
	}

	@Override
	public Connection newInstance() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO enhancement, hiding
			throw new IllegalStateException("uable to create connection", e);
		}
	}

	private class ConnectionHandler implements InvocationHandler {

		Connection connection;

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (method.getName().equals("close")) {
				// override for close method
				pool.returnObj(connection);
				return null;
			} else {
				return method.invoke(connection, args);
			}
		}

	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return new PrintWriter(System.out);
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

}
