package org.tempuri.purchaseorderschema;

import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class XMLParsing {
	public static void main(String[] args) throws JAXBException, SAXException {
		JAXBContext context = JAXBContext.newInstance("org.tempuri.purchaseorderschema");
		Unmarshaller unmarshaller = context.createUnmarshaller();

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
		Schema schema = schemaFactory.newSchema(new StreamSource(Paths.get("./address.xsd").toFile()));
		unmarshaller.setSchema(schema);

		StreamSource source = new StreamSource(Paths.get("./order.xml").toFile());
		JAXBElement<PurchaseOrderType> element = unmarshaller.unmarshal(source, PurchaseOrderType.class);
		PurchaseOrderType orderType = element.getValue();
		System.out.println(orderType.getBillTo().getCountry());
	}
}
