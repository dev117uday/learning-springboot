package com.example.camelmsa.routes;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RestAPIConsumerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration().host("localhost:8081");

        from("timer:rest-api-consumer?period=3000")
                .setHeader("from", () -> "usd")
                .setHeader("to", () -> "inr")
                .to("rest:get:/currency-exchange/from/{from}/to/{to}")
                .log("${body}");
    }
}
