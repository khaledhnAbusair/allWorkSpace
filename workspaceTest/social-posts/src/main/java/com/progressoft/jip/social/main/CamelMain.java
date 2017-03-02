package com.progressoft.jip.social.main;

import java.util.Collection;
import java.util.Map;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelMain {
	public static void main(String[] args) throws Exception {
		ApplicationContext springContext = new ClassPathXmlApplicationContext(".beans.xml");

		Main main = new Main();
		CamelContext context = new DefaultCamelContext();

		ActiveMQComponent mqComponent = new ActiveMQComponent();
		mqComponent.setBrokerURL("tcp://localhost:61616");
		context.addComponent("activemq", mqComponent);

		Map<String, RouteBuilder> routesMap = springContext.getBeansOfType(RouteBuilder.class);
		Collection<RouteBuilder> routes = routesMap.values();
		for (RouteBuilder routeBuilder : routes) {
			context.addRoutes(routeBuilder);
		}

		main.getCamelContexts().add(context);
		main.run();
	}
}