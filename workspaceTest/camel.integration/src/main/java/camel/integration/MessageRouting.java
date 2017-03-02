package camel.integration;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

public class MessageRouting {

	private static final String ENDPOINT = "file:/home/khaled/Desktop/camel";

	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		Main main = new Main();

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from(ENDPOINT).process((Exchange exchange) -> {
					Message inMessage = exchange.getIn();
					String body = inMessage.getBody(String.class);
					System.out.println("body is  : " + body);
					Message outMessage = exchange.getOut();
					outMessage.setBody("khaled abusair" + body);

				}).to("file:/home/khaled/Desktop/camelTwo");
			}
		});

		main.getCamelContexts().add(context);
		main.run();
		System.out.println("Done");
	}
}
