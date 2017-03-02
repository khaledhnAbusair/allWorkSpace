package com.progressoft.jip.domxmltraining;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParserDemo {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(new FileInputStream("employee.xml"));
		NodeList nodeList = document.getElementsByTagName("employee");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			System.out.println("------------");
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				System.out.println("Employee Id :  " + element.getAttribute("id"));
				System.out.println("FirstName :" + element.getElementsByTagName("firstname").item(0).getTextContent());
				System.out.println("LastName :" + element.getElementsByTagName("lastname").item(0).getTextContent());
				System.out.println("Location :" + element.getElementsByTagName("location").item(0).getTextContent());

			}

		}

	}

}
