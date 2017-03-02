package com.progressoft.jip.parsing.csv;

import static com.progressoft.jip.parsing.framework.Constant.COMMA;
import static com.progressoft.jip.parsing.framework.Constant.DISABLE_SSL;
import static com.progressoft.jip.parsing.framework.Constant.TABLE_PREFIX;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

import com.progressoft.jip.parsing.databaseimpl.JDBCDBOperation;
import com.progressoft.jip.parsing.framework.Parser;
import com.progressoft.jip.parsing.importer.XMLSettings;
import com.progressoft.jip.parsing.report.ReportListener;

@SuppressWarnings("unused")
public class CSVParser implements Parser {

	private BasicDataSource dataSource = new BasicDataSource();;
	private JDBCDBOperation jdbcdbOperation;
	private Map<String, String> records;
	private List<String> columnsNames;
	private List<String> columnsValue;
	private String line = "";
	private String tableName;
	private String[] split;
	private Path path;
	private ReportListener report;

	public CSVParser(Path path) {
		this.path = path;
	}

	@Override
	public void parseDocument(ReportListener genrateReport) {
		jdbcdbOperation = new JDBCDBOperation(dataSource, genrateReport, path);
		report = genrateReport;
		try (FileInputStream fis = new FileInputStream(path.toFile());
				BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {

			moveToNextLine(br);
			configurationDataSource();

			while ((line = br.readLine()) != null) {
				processRow();
			}

		} catch (Exception e) {
			report.updateListener(e);
			throw new IllegalStateException(e);
		}finally{
			report.generateReport();
		}

		
	}

	private void iterateColumns() {
		records = new LinkedHashMap<>();
		for (int i = 0; i < columnsNames.size(); i++) {
			records.put(columnsNames.get(i), columnsValue.get(i));
		}
	}

	private void processRow() throws Exception {
		split = split();
		if (line.startsWith(TABLE_PREFIX)) {
			initializeTableName(split);
			addColumnsName(split);
			jdbcdbOperation.flush();
			jdbcdbOperation.generateInsertStatment(tableName, columnsNames);
		} else {
			addColumnsValue(split);
			iterateColumns();
			jdbcdbOperation.batchRecord(records);
		}
	}

	private String[] split() {
		String[] split;
		split = line.split(COMMA);
		return split;
	}

	private void initializeTableName(String[] split) {
		tableName = split[0].replace(TABLE_PREFIX, "");
	}

	private void moveToNextLine(BufferedReader br) throws IOException {
		line = br.readLine();
	}

	private void configurationDataSource() {
		String[] split = line.split(COMMA);
		dataSource.setUsername(split[0]);
		dataSource.setPassword(split[1]);
		dataSource.setUrl(split[2] + DISABLE_SSL);
		dataSource.setDriverClassName(split[3]);
	}

	private void addColumnsValue(String[] split) {
		columnsValue = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			columnsValue.add(split[i]);
		}
	}

	private void addColumnsName(String[] split) {
		columnsNames = new ArrayList<>();
		for (int i = 1; i < split.length; i++) {
			columnsNames.add(split[i]);
		}
	}

}
