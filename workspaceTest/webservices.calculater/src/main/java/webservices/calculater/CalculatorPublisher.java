package webservices.calculater;

import javax.xml.ws.Endpoint;

import webservices.calculater.impl.CalculatorImpl;

public class CalculatorPublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:7071/calculator", new CalculatorImpl());
		System.out.println("Done");
	}
}
