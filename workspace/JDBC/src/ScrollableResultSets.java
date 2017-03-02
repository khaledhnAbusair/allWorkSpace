import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScrollableResultSets {
	public static void main(String[] args) throws SQLException {
		// DriverManager.registerDriver(new org.sqlite.JDBC());
		String url = "jdbc:mysql://localhost:3306/employees";
		try (Connection connection = DriverManager.getConnection(url, "root", "root")) {
			String sql = "select birth_date bd, emp_no en,first_name fn,last_name ln, gender g,hire_date hd "
					+ "from employees " + "where gender = 'F' and first_name like '%khaled%' "
					+ "and (last_name like 'B%' or last_name = 'abdellatif')";
			DatabaseMetaData metaData = connection.getMetaData();
			System.out.println(metaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
			System.out.println(metaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE));
			try (PreparedStatement statement = connection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE)) {
				ResultSet rs = statement.executeQuery();
				
				int count = 1;
				while (rs.next()) {
					System.out.printf("%2d %d %s %s%n", count++, rs.getInt("en"), rs.getString("fn"),
							rs.getString("ln"));
				}

				rs.absolute(6);
				System.out.println("========iterate another time=========");
				while (rs.next()) {
					System.out.printf("%d %s %s%n", rs.getInt("en"), rs.getString("fn"), rs.getString("ln"));
				}

				// rs.moveToInsertRow();
				//
				// rs.updateInt("en", 499999 + 1);
				// rs.updateString("fn", "Mohammad");
				// rs.updateString("ln", "Abdellatif");
				// rs.updateDate("hd", new
				// java.sql.Date(System.currentTimeMillis()));
				// rs.updateString("g", "M");
				// rs.updateDate("bd", new java.sql.Date(LocalDate.of(1985, 3,
				// 5).toEpochDay()));
				//
				// rs.insertRow();
				
				rs.last();
				System.out.println(rs.getString("en"));
				rs.updateDate("bd", new java.sql.Date(System.currentTimeMillis()));
				
				rs.updateRow();
				
				System.out.println("done");
			}
		}
	}
}
