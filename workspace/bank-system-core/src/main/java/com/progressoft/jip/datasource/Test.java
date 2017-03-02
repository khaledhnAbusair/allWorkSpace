package com.progressoft.jip.datasource;

import java.sql.SQLException;

public class Test {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		JDBCSettings.Builder builder = new JDBCSettings.Builder();

		builder.setPassword("");
		builder.setDriverClass("com.mysql.jdbc.Driver");
		builder.setUsername("");
		builder.setUrl("jdbc:mysql://localhost/BankSystem");

	}
}
