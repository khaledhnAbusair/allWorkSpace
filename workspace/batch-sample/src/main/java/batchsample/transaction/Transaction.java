/**
 * 
 */
package batchsample.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author phi01tech
 *
 */
public class Transaction {

	private static final ThreadLocal<Connection> transactionLocal = new ThreadLocal<>();

	public static void startTransaction(Connection connection) {
		// TODO check if already existsd
		transactionLocal.set(connection);
	}

	public static boolean isTransacted() {
		return transactionLocal.get() != null;
	}

	public static Connection getTransactedConnection() {
		Connection connection = transactionLocal.get();
		if (connection == null) {
			throw new IllegalStateException("No transaction started");
		}
		return connection;
	}

	public static void end() throws SQLException {
		Connection connection = getTransactedConnection();
		connection.close();
		transactionLocal.remove();
	}

	public static void commit() throws SQLException {
		getTransactedConnection().commit();
	}

	public static void rollback() throws SQLException {
		getTransactedConnection().rollback();
	}

}
