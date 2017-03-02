package com.progressoft.jip.parsing.xml;

import static com.progressoft.jip.parsing.framework.Constant.CONNECTION_SETTINGS;
import static com.progressoft.jip.parsing.framework.Constant.DISABLE_SSL;
import static com.progressoft.jip.parsing.framework.Constant.DRIVER_CLASS_NAME;
import static com.progressoft.jip.parsing.framework.Constant.PASSWORD;
import static com.progressoft.jip.parsing.framework.Constant.URL;
import static com.progressoft.jip.parsing.framework.Constant.USERNAME;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.dbcp.BasicDataSource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.progressoft.jip.parsing.framework.Parser;

public class XMLParserConnection extends MyDefaultHandler implements Parser {

	private BasicDataSource dataSource = new BasicDataSource();
	private XMLParserRecords parserRecords;
	private FileInputStream inputStream;
	private boolean isDriverClass = false;
	private boolean isPassword = false;
	private boolean isUserName = false;
	private boolean isURL = false;
	private Path path;
	//private Report report;

	public XMLParserConnection(Path path) {
		this.path = path;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase(CONNECTION_SETTINGS)) {
		} else if (qName.equalsIgnoreCase(USERNAME)) {
			isUserName = true;
		} else if (qName.equalsIgnoreCase(PASSWORD)) {
			isPassword = true;
		} else if (qName.equalsIgnoreCase(URL)) {
			isURL = true;
		} else if (qName.equalsIgnoreCase(DRIVER_CLASS_NAME)) {
			isDriverClass = true;
		}
		System.out.println(qName + "Con");

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase(CONNECTION_SETTINGS)) {
			parserRecords = new XMLParserRecords(dataSource, path);
			parserRecords.parseDocument();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		if (isUserName) {
			dataSource.setUsername(new String(ch, start, length));
			isUserName = false;
		} else if (isPassword) {
			dataSource.setPassword(new String(ch, start, length));
			isPassword = false;
		} else if (isURL) {
			dataSource.setUrl(new String(ch, start, length) + DISABLE_SSL);
			isURL = false;
		} else if (isDriverClass) {
			dataSource.setDriverClassName(new String(ch, start, length));
			isDriverClass = false;
		}
	}

	@Override
	public void parseDocument() {
		try {
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			inputStream = new FileInputStream(path.toFile());
			saxParser.parse(inputStream, this);
		} catch (SAXException | ParserConfigurationException | IOException e) {
			//report.identifyExceptionType(e);
		}
	}

	public Schema getSchema(File schemaPath) throws SAXException {
		SchemaFactory factoryS = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factoryS.newSchema(schemaPath);
		return schema;
	}

	@Override
	public BasicDataSource getDataState() {
		return dataSource;
	}
}