/**
 * 
 */
package com.progressoft.jip.transaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author khaled
 *
 */
public class JDBCTransaction {
	private static final ThreadLocal<Connection> connections = new ThreadLocal<>();
	private static List<String> namesToIgnore = Arrays.asList("setAutoCommit", "close", "commit", "rollback");

	public static void beginTransaction(Connection connection) throws SQLException {
		// TODO check if connection is null or not and active

		connection.setAutoCommit(false);
		connections.set(connection);
	}

	public static Connection getTransactivedConnection() {
		// TODO more work to do

		Connection connection = connections.get();

		ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();
		Class<Connection> interfaceToImplemnt = Connection.class;

		Connection decorated = (Connection) Proxy.newProxyInstance(threadClassLoader,
				new Class[] { interfaceToImplemnt }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (namesToIgnore.contains(method.getName())) {
							return null;
						}
						return method.invoke(connection, args);
					}
				});

		return decorated;
	}

	public static boolean isTransacted() {
		return connections.get() != null;
	}
}
