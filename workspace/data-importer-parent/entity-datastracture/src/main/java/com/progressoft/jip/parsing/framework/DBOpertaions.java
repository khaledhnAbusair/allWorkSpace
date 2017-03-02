package com.progressoft.jip.parsing.framework;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

public interface DBOpertaions {

	public void generateInsertStatment(String tableName, Collection<String> columnNames) throws SQLException;

	public void batchRecord(Map<String, String> records) throws SQLException;

	public void excuteAndCommitBatch() throws SQLException;

	public void flush() throws SQLException;

}