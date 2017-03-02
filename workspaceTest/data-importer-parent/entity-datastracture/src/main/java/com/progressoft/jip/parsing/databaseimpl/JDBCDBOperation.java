package com.progressoft.jip.parsing.databaseimpl;

import static com.progressoft.jip.parsing.framework.Constant.COMMA;
import static com.progressoft.jip.parsing.framework.Constant.INSERT_INTO;
import static com.progressoft.jip.parsing.framework.Constant.LEFTPARENTHESES;
import static com.progressoft.jip.parsing.framework.Constant.QUASTIONMARK;
import static com.progressoft.jip.parsing.framework.Constant.RIGHTPARENTHESES;
import static com.progressoft.jip.parsing.framework.Constant.VALUES;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

import com.progressoft.jip.parsing.framework.DBOpertaions;
import com.progressoft.jip.parsing.report.Report;

public class JDBCDBOperation implements DBOpertaions {
	private List<String> columns = new ArrayList<>();
	private BasicDataSource dataSource;
	private PreparedStatement statement;
	private int statmentCounter = 0;
	private int MaxBatchSize = 10000;
	private Connection connection;
	private Report report;

	private Path path;

	public JDBCDBOperation(BasicDataSource dataSource, Path path) {
		this.dataSource = dataSource;
		this.path = path;
		this.report = new Report();
		this.report.setStartParsingTime(new Date());
	}

	@Override
	public void generateInsertStatment(String tableName, Collection<String> records) throws SQLException {
		try {
			connection = dataSource.getConnection();
			String sql = generateSQLStatement(tableName, records);
			statement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			
			throw new SQLException(e);
		}
	}

	@Override
	public void batchRecord(Map<String, String> records) throws SQLException {

		try {
			connection.setAutoCommit(false);
			int current = 1;
			for (Map.Entry<String, String> entry : records.entrySet()) {
				statement.setObject(current++, entry.getValue());
			}
			statement.addBatch();
			statmentCounter++;
			if (statmentCounter == MaxBatchSize)
				excuteAndCommitBatch();

		} catch (SQLException e) {
			this.report.addError(e.getMessage(), statement.toString());

		} finally {

		}
	}

	@Override
	public void flush() throws SQLException {
		if (statmentCounter > 0)
			excuteAndCommitBatch();
		if (connection != null)
			connection.close();
		report.print();
	}

	@Override
	public void excuteAndCommitBatch() throws SQLException {
		statement.executeBatch();
		connection.commit();
	}

	private String generateSQLStatement(String tableName, Collection<String> records) {

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

}
