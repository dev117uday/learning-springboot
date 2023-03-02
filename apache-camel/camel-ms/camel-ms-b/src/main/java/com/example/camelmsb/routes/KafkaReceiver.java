package com.example.camelmsb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class KafkaReceiver extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("kafka:mykafkatopic")
                .to("log:kafka-logs");
    }
}
