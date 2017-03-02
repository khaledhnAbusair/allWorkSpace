import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class QueryTest2 {
	public static void main(String[] args) throws SQLException {
//		DriverManager.registerDriver(new org.sqlite.JDBC());
		String url = "jdbc:sqlite:C:\\Courses\\OCP8\\employees\\employees.db";
		System.out.println("please enter user id:");
		try (Connection connection = DriverManager.getConnection(url)) {
			String sql = "select user_id u_id,first_name, last_name from employees";
			System.out.println(sql);
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet result = statement.executeQuery()) {
					iterateOverResult(result);
				}
			}
		}
	}

	private static void iterateOverResult(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		Map<Integer, String> columnNames = new HashMap<>();
		int columnCount = metaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			columnNames.put(i, metaData.getColumnName(i));
		}

		String format = "%-10s|";
		for (int i = 1; i <= columnCount; i++) {
			System.out.printf(format, columnNames.get(i));
		}
		System.out.println("");
		while (result.next()) {
			for (int i = 1; i <= columnCount; i++) {
				String columnName = columnNames.get(i);
				System.out.printf(format, result.getString(columnName));
			}
			System.out.println();
		}

	}
}
