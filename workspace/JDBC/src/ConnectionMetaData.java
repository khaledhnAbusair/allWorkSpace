import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionMetaData {
	public static void main(String[] args) throws SQLException {
		// DriverManager.registerDriver(new org.sqlite.JDBC());
		String url = "jdbc:sqlite:C:\\Courses\\OCP8\\employees\\employees.db";
//		String url = "jdbc:mysql://localhost:3306/employees";
		try (Connection connection = DriverManager.getConnection(url, "root", "root")) {
			DatabaseMetaData metaData = connection.getMetaData();
			System.out.println(metaData.getDatabaseProductName());
			System.out.println(metaData.getDatabaseMajorVersion());
			System.out.println(metaData.getDatabaseMinorVersion());

			System.out.println("========PRINT CATALOGS=======");
			try (ResultSet catalogs = metaData.getCatalogs()) {
				while (catalogs.next()) {
					System.out.println(catalogs.getString("TABLE_CAT"));
				}
			}
			System.out.println("========SCHEMA=======");
			try (ResultSet schemas = metaData.getSchemas()) {
				while (schemas.next()) {
					System.out.println(schemas.getString("TABLE_SCHEM"));
				}
			}
			try (ResultSet tables = metaData.getTables("employees", "", "", null)) {
				while (tables.next()) {
					System.out.println("table name: " + tables.getString("TABLE_NAME"));
				}
			}

			try (ResultSet types = metaData.getTypeInfo()) {
				while (types.next()) {
					System.out.println(types.getString("TYPE_NAME") + "," + types.getInt("DATA_TYPE"));
				}
			}
			
			System.out.println(metaData.supportsAlterTableWithAddColumn());

		}
	}

}
