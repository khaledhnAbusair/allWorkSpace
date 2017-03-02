package com.progressoft.jip.domxmltraining;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseUnknownXMLStructure {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Build Document
		Document document = builder.parse(new File("employee.xml"));

		// Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();

		// Here comes the root node
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());

		// Get all employees
		NodeList nList = document.getElementsByTagName("employee");
		System.out.println("============================");

		visitChildNodes(nList);
	}

	private static void visitChildNodes(NodeList nList) {

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("Node Name = " + node.getNodeName() + "Value = " + node.getTextContent());
				if (node.hasAttributes()) {
					NamedNodeMap nodeMap = node.getAttributes();
					for (int index = 0; index < nodeMap.getLength(); index++) {
						Node tempNode = nodeMap.item(index);
						System.out.println(
								"Attr name : " + tempNode.getNodeName() + "; Value = " + tempNode.getNodeValue());
					}
					if (node.hasChildNodes()) {
						// We got more child's; Let's visit them as well
						visitChildNodes(node.getChildNodes());
					}
				}
			}
		}
	}
}
