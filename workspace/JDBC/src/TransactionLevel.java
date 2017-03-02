import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransactionLevel {
	public static void main(String[] args) {
		// DriverManager.registerDriver(new org.sqlite.JDBC());
		// String url =
		// "jdbc:sqlite:C:\\Courses\\OCP8\\employees\\employees.db";
		String url = "jdbc:mysql://localhost:3306/employees";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, "root", "root");
			DatabaseMetaData metaData = connection.getMetaData();
			metaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
			connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			connection.setAutoCommit(false);
			String sql = "select * from employees where emp_no in(500000,465357)";
			try (Statement statement = connection.createStatement()) {
				ResultSet resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
					System.out.printf("%d %s %s%n", resultSet.getInt("emp_no"), resultSet.getString("first_name"),
							resultSet.getString("last_name"));
				}
			}
			new Scanner(System.in).nextLine();
			try (Statement statement = connection.createStatement()) {
				ResultSet resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
					System.out.printf("%d %s %s%n", resultSet.getInt("emp_no"), resultSet.getString("first_name"),
							resultSet.getString("last_name"));
				}
			}
			System.out.println("enter to continue");
			connection.commit();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
				}
			}
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e1) {
				}
			}
		}
	}

}
