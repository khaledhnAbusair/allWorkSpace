import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QueryTest {
	public static void main(String[] args) throws SQLException {
		// register driver, I am telling driver manager that I have a new driver
		DriverManager.registerDriver(new org.sqlite.JDBC());
		// connect to a database (URL of the database)
		// which database to connect to, and what driver to use
		String url = "jdbc:sqlite:C:\\Courses\\OCP8\\employees\\employees.db";
		Scanner scanner = new Scanner(System.in);
		System.out.println("please enter user id:");
		String userId = scanner.nextLine();
		// System.out.println("please enter name (or part of it)");
		// String name = scanner.nextLine();
		// check all drivers then get the connection from first one matched
		try (Connection connection = DriverManager.getConnection(url)) {
			// you are connected to the database, your session
			String sql = "select user_id u_id,first_name, last_name " + "from employees where user_id = ?";
			System.out.println(sql);
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, userId);
				// statement.setString(2, "%" + name + "%");
				try (ResultSet result = statement.executeQuery()) {
					System.out.println("done");
					while (result.next()) {
						String id = result.getString(1);
						id = result.getString("u_id");
						String firstName = result.getString("first_name");
						String lastName = result.getString("last_name");
						System.out.println(id + "," + firstName + "," + lastName);
					}
				}
			}
		}
	}
}
