package com.progressoft.jip.parsing.xml;

import static com.progressoft.jip.parsing.framework.Constant.DATA;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.dbcp.BasicDataSource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.progressoft.jip.parsing.databaseimpl.JDBCDBOperation;
import com.progressoft.jip.parsing.framework.DBOpertaions;
import com.progressoft.jip.parsing.framework.Parser;

public class XMLParserRecords extends MyDefaultHandler implements Parser {

	private DBOpertaions dbOpertaions;
	private String oldTableName;
	private boolean insideData;
	private Path path;
	private BasicDataSource dataSource;

	public XMLParserRecords(BasicDataSource dataSource, Path path) {
		this.path = path;
		this.dataSource = dataSource;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase(DATA)) {
			insideData = true;
			return;
		}
		if (insideData) {
			LinkedHashMap<String, String> fields = new LinkedHashMap<>();
			for (int index = 0; index < attributes.getLength(); index++) {
				fields.put(attributes.getLocalName(index), attributes.getValue(index));
			}
			try {
				executeInsert(qName, fields);
			} catch (SQLException e) {
				throw new SAXException(e);
			}
		}
		oldTableName = qName;
	}

	private void executeInsert(String currentTableName, Map<String, String> columnValues) throws SQLException {
		if (!oldTableName.equals(currentTableName)) {
			dbOpertaions.flush();
			dbOpertaions.generateInsertStatment(currentTableName, columnValues.keySet());
		}
		dbOpertaions.batchRecord(columnValues);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals(DATA)) {
			insideData = false;
			try {
				dbOpertaions.flush();
			} catch (SQLException e) {
				throw new SAXException(e);
			}
		}
	}

	@Override
	public void parseDocument() {
		dbOpertaions = new JDBCDBOperation(dataSource, path);
		try (FileInputStream inputStream = new FileInputStream(path.toFile())) {
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			saxParser.parse(inputStream, this);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
		}

	}

	public Schema getSchema(File schemaPath) throws SAXException {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(schemaPath);
		return schema;
	}

	@Override
	public BasicDataSource getDataState() {
		return null;
	}
}
