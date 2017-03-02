package com.progressoft.jip.parsing.importer;

import static com.progressoft.jip.parsing.framework.Constant.ERROR_PATH;
import static com.progressoft.jip.parsing.framework.Constant.IMPORT_SETTINGS;
import static com.progressoft.jip.parsing.framework.Constant.SOURCE_PATH;
import static com.progressoft.jip.parsing.framework.Constant.SUCCESS_PATH;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.progressoft.jip.parsing.framework.Parser;

public class DataImporter extends DefaultHandler implements Parser {

	private boolean isSuccessPath = false;
	private boolean isSourcePath = false;
	private boolean isErrorPath = false;
	private FileInputStream inputStream;
	private List<Path> successPath;
	private List<Path> sourcePath;
	private List<Path> errorPath;
	private Path settingXMLPath;
	// private Report report;

	public DataImporter(Path settingXMLPath) {
		this.settingXMLPath = settingXMLPath;
		successPath = new ArrayList<>();
		sourcePath = new ArrayList<>();
		errorPath = new ArrayList<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase(IMPORT_SETTINGS)) {
		} else if (qName.equalsIgnoreCase(SOURCE_PATH)) {
			isSourcePath = true;
		} else if (qName.equalsIgnoreCase(SUCCESS_PATH)) {
			isSuccessPath = true;
		} else if (qName.equalsIgnoreCase(ERROR_PATH)) {
			isErrorPath = true;
		}

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (isSourcePath) {
			sourcePath.add(Paths.get(new String(ch, start, length)));
			isSourcePath = false;
		} else if (isSuccessPath) {
			successPath.add(Paths.get(new String(ch, start, length)));
			isSuccessPath = false;
		} else if (isErrorPath) {
			errorPath.add(Paths.get(new String(ch, start, length)));
			isErrorPath = false;
		}
	}

	@Override
	public void parseDocument() {
		try {
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			inputStream = new FileInputStream(settingXMLPath.toFile());
			saxParser.parse(inputStream, this);

		} catch (SAXException | ParserConfigurationException | IOException e) {
			// report.identifyExceptionType(e);
		}
	}

	public List<Path> getSourcePath() {
		return sourcePath;
	}

	public List<Path> getSuccessPath() {
		return successPath;
	}

	public List<Path> getErrorPath() {
		return errorPath;
	}
}
