package com.progressoft.jip.parsing.framework;

import java.util.Collection;
import java.util.Map;

public interface DBOpertaions {

	public void generateInsertStatment(String tableName, Collection<String> columnNames);

	public void batchRecord(Map<String, String> records);

	public void excuteAndCommitBatch();

	public void flush();

}