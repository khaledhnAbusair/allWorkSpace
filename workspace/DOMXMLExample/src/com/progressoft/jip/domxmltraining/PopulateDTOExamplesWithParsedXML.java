package com.progressoft.jip.domxmltraining;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PopulateDTOExamplesWithParsedXML {

	public static void main(String[] args) {

		try {
			List<Employee> employees = parseEmployeeXML();

			System.out.println(employees.toString());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private static List<Employee> parseEmployeeXML() throws ParserConfigurationException, SAXException, IOException {

		List<Employee> employees = new ArrayList<>();
		Employee employee = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(new File("employee.xml"));
		document.getDocumentElement().normalize();
		NodeList nodeList = document.getElementsByTagName("employee");

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;

				employee = new Employee();
				employee.setId(Integer.parseInt(element.getAttribute("id")));
				employee.setFirstName(element.getElementsByTagName("firstname").item(0).getTextContent());
				employee.setLastName(element.getElementsByTagName("lastname").item(0).getTextContent());
				employee.setLocation(element.getElementsByTagName("location").item(0).getTextContent());

				employees.add(employee);
			}

		}
		return employees;
	}

}
