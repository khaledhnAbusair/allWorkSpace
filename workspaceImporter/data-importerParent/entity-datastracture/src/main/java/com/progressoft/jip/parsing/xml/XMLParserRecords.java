package com.progressoft.jip.parsing.xml;

import static com.progressoft.jip.parsing.framework.Constant.DATA;
import static com.progressoft.jip.parsing.framework.Constant.IMPORT_REQUEST;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
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

	private FileInputStream inputStream;
	private DBOpertaions dbOpertaions;
	private String oldTableName;
	private boolean insideData;
	private Path path;
	// private Report report;

	public XMLParserRecords(BasicDataSource dataSource, Path path) {
		dbOpertaions = new JDBCDBOperation(dataSource);
		this.path = path;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println(qName);
		System.out.println(localName);
		if (qName.equalsIgnoreCase(DATA)) {
			insideData = true;
			return;
		}
		if (insideData) {
			LinkedHashMap<String, String> records = new LinkedHashMap<>();
			for (int index = 0; index < attributes.getLength(); index++) {
				records.put(attributes.getLocalName(index), attributes.getValue(index));
			}
			excuteInsert(qName, records);
		}
		oldTableName = qName;
	}

	private void excuteInsert(String currentTableName, Map<String, String> columnValues) {
		if (!oldTableName.equals(currentTableName)) {
			dbOpertaions.flush();
			dbOpertaions.generateInsertStatment(currentTableName, columnValues.keySet());
		}
		dbOpertaions.batchRecord(columnValues);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals(DATA))
			insideData = false;
		if (qName.equals(IMPORT_REQUEST))
			dbOpertaions.flush();
	}

	@Override
	public void parseDocument() {
		try {
			inputStream = new FileInputStream(path.toFile());
			SAXParserFactory.newInstance().newSAXParser().parse(inputStream, this);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// report.identifyExceptionType(e);
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
