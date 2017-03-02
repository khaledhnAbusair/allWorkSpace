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
import com.progressoft.jip.parsing.report.ReportListener;

public class SettingsReader extends DefaultHandler implements Parser {

	private boolean isSuccessPath = false;
	private boolean isSourcePath = false;
	private boolean isErrorPath = false;
	private Path xmlPath;
	private List<XMLSettings> xmlSettings = new ArrayList<>();
	private XMLSettings settings;

	public SettingsReader(Path xmlPath) {
		this.xmlPath = xmlPath;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase(IMPORT_SETTINGS)) {
			settings = new XMLSettings();
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
			settings.setSourcePath(Paths.get(new String(ch, start, length)));
			isSourcePath = false;
		} else if (isSuccessPath) {
			settings.setSuccessPath(Paths.get(new String(ch, start, length)));
			isSuccessPath = false;
		} else if (isErrorPath) {
			settings.setErrorPath(Paths.get(new String(ch, start, length)));
			isErrorPath = false;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase(IMPORT_SETTINGS)) {
			xmlSettings.add(settings);
		}
	}

	@Override
	public void parseDocument(ReportListener genrateReport) {
		try (FileInputStream inputStream = new FileInputStream(xmlPath.toFile())) {
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			saxParser.parse(inputStream, this);
		} catch (SAXException | ParserConfigurationException | IOException e) {
			throw new IllegalStateException(e);
		}

	}

	public List<XMLSettings> getXMLSettings() {
		return xmlSettings;
	}

}
