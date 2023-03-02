package com.example.camelmsa.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMqSender extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:active-mq-timer?period=2000")
                .transform().constant("my message for active-mq")
                .log("--- message sent to queue ---")
                .to("activemq:my-activemq-queue");

        from("file:files/json")
                .to("activemq:my-activemq-queue");

        from("file:files/xml")
                .to("activemq:my-activemq-xml-queue");

    }
}
