import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class Batchupdate {
	public static void main(String[] args) throws SQLException {
		// DriverManager.registerDriver(new org.sqlite.JDBC());
		String url = "jdbc:mysql://localhost:3306/employees";
		try (Connection connection = DriverManager.getConnection(url, "root", "root")) {
			String sql = "update employees set first_name = upper(first_name) "
					+ "where gender = ? and first_name like '%khaled%' and last_name like 'B%'";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, "M");
				statement.addBatch();
				
				statement.setString(1, "F");
				statement.addBatch();
				
				int[] executeBatch = statement.executeBatch();
				System.out.println(Arrays.toString(executeBatch));
				System.out.println("done");
			}
		}
	}
}
