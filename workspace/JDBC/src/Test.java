import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	public static void main(String[] args) throws SQLException {
		// register driver, I am telling driver manager that I have a new driver
		DriverManager.registerDriver(new org.sqlite.JDBC());
		// connect to a database (URL of the database)
		// which database to connect to, and what driver to use
		String url = "jdbc:sqlite:C:\\Courses\\OCP8\\employees\\employees.db";
		// check all drivers then get the connection from first one matched
		try (Connection connection = DriverManager.getConnection(url)) {
			// you are connected to the database, your session
			try (Statement statement = connection.createStatement()) {
				int updated = statement
						.executeUpdate("insert into employees values ('u268','MOhammad','abdellatif','15/10/1985')");
				System.out.println(updated);
				System.out.println("done");
			}
		}
	}
}
