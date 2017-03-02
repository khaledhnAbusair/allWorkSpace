package org.tempuri.purchaseorderschema;

import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

public class XMLBuilding {
	public static void main(String[] args) throws JAXBException {

		PurchaseOrderType order = new PurchaseOrderType();
		USAddress shippingAddress = new USAddress();

		shippingAddress.setCity("Indianpolis");
		shippingAddress.setCountry("America");
		shippingAddress.setStreet("Palastine");
		shippingAddress.setName("Khaled");
		shippingAddress.setState("Indiana");
		shippingAddress.setZip(new BigInteger("1234"));

		order.getShipTo().add(shippingAddress);

		USAddress shippingAddress2 = new USAddress();

		shippingAddress.setCity("Munekh");
		shippingAddress.setCountry("Germany");
		shippingAddress.setStreet("Palastine");
		shippingAddress.setName("Sameer");
		shippingAddress.setState("LALALALAL");
		shippingAddress.setZip(new BigInteger("15234"));

		order.setBillTo(shippingAddress2);

		JAXBContext jaxbContext = JAXBContext.newInstance("org.tempuri.purchaseorderschema");
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		QName qName = new QName("PurchaseOrder");
		JAXBElement<PurchaseOrderType> element = new JAXBElement<PurchaseOrderType>(qName, PurchaseOrderType.class,
				order);
		marshaller.marshal(element, System.out);
	}
}
