package com.progressoft.jip.parsing.xml;

import static com.progressoft.jip.parsing.framework.Constant.CONNECTION_SETTINGS;

import java.nio.file.Path;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLStateParser extends DefaultHandler {

	private MyDefaultHandler handler;
	private Path path;

	public XMLStateParser(Path path) {
		handler = new XMLParserConnection(path);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		handler.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals(CONNECTION_SETTINGS)) {
			handler = new XMLParserRecords(handler.getDataState(), path);
		}
		handler.endElement(uri, localName, qName);
	}

	@Override
	public void startDocument() throws SAXException {
		handler.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		handler.endDocument();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		handler.characters(ch, start, length);
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		handler.startPrefixMapping(prefix, uri);
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		handler.endPrefixMapping(prefix);
	}

}
