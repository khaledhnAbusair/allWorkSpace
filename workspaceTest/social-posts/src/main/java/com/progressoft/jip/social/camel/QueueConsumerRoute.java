package com.progressoft.jip.social.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.progressoft.jip.social.messaging.PostMessageHandler;
import com.progressoft.jip.social.messaging.PostMessageResult;
import com.progressoft.jip.social.messaging.PostMessageResult.Status;

@Component
public class QueueConsumerRoute extends RouteBuilder {

	@Autowired
	private PostMessageHandler handler;

	@Override
	public void configure() throws Exception {
		from("activemq:queue:say_hello_request")
				.to("dataformat:jaxb:unmarshal?contextPath=com.progressoft.jip.social.messaging")
				.bean(handler, "processMessage").setBody().constant(new PostMessageResult()).process((e) -> {
					e.getIn(PostMessageResult.class).setStatus(Status.SUCCESS);
				}).to("dataformat:jaxb:marshal?contextPath=com.progressoft.jip.social.messaging.messaging")
				.to("activemq:queue:say_hello_result");
	}
}
