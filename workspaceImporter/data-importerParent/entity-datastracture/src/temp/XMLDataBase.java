package com.progressoft.jip.xmltest;

import static com.progressoft.jip.xmltest.Constant.*;
import org.apache.commons.dbcp.BasicDataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XMLDataBase implements DBOpertaions {

	private List<String> columns = new ArrayList<>();
	private BasicDataSource dataSource;
	private PreparedStatement statement;
	private int statmentCounter = 0;
	private int MaxBatchSize = 10000;
	private Connection connection;

	public XMLDataBase(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}

	// TODO introduce RecordTO
	@Override
	public void generateInsertStatment(String tableName, Set<String> records) {
		try {
			connection = dataSource.getConnection();
			String sql = generateSQLStatement(tableName, records);
			statement = connection.prepareStatement(sql);

		} catch (SQLException e) {
			// TODO throw your own exception types
			throw new IllegalStateException(e);
		}
	}

	private String generateSQLStatement(String tableName, Set<String> records) {
		String columnsQuery = LEFTPARENTHESES;
		String values = LEFTPARENTHESES;
		for (String column : records) {
			columnsQuery += column + COMMA;
			columns.add(column);
			values += QUASTIONMARK + COMMA;
		}
		columnsQuery = columnsQuery.substring(0, columnsQuery.length() - 1) + RIGHTPARENTHESES;
		values = values.substring(0, values.length() - 1) + RIGHTPARENTHESES;
		String sql = INSERT_INTO + tableName + columnsQuery + VALUES + values;
		return sql;
	}

	@Override
	public void batchRecord(Map<String, String> records) {
		try {
			connection.setAutoCommit(false);
			int current = 1;
			for (Map.Entry<String, String> entry : records.entrySet()) {
				statement.setObject(current++, entry.getValue());
			}

			statement.addBatch();
			statmentCounter++;

			excuteAndCommitBatch();

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void flush() {
		try {
			if (statmentCounter > 0) {
				excuteAndCommitBatch();
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);

		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void excuteAndCommitBatch() {
		try {
			if (statmentCounter == MaxBatchSize) {
				statement.executeBatch();
				connection.commit();
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
