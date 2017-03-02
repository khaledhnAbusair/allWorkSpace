package camel.integration;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.camel.component.ActiveMQConfiguration;
import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class SendMessageToJMS {

	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		ActiveMQConfiguration config = new ActiveMQConfiguration();
		config.setBrokerURL("tcp://localhost:61616");
		Component component = new ActiveMQComponent(config);

		context.addComponent("mq", component);

		context.start();

		ProducerTemplate producer = context.createProducerTemplate();

		producer.sendBody("file:c:/temp/camel?fileName=result.txt&fileExist=Append", "Hello world2");
		System.out.println("done file");
		producer.sendBody("mq:queue:process_request", "this is a message to JMS");

		ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
		String receiveBody = consumerTemplate.receiveBody("mq:queue:response_queue", String.class);
		System.out.println("received: " + receiveBody);
	}

}
