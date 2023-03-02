package com.example.camelmsb.routes;

import com.example.camelmsb.model.CurrencyExchange;
import com.example.camelmsb.model.MyCurrencyExchangeProcessor;
import com.example.camelmsb.model.MyCurrencyExchangeTransformer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMqReceiverRouter extends RouteBuilder {

    @Autowired
    private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;

    @Autowired
    private MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

    @Override
    public void configure() throws Exception {

        from("activemq:my-activemq-queue")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .bean(myCurrencyExchangeProcessor)
                .bean(myCurrencyExchangeTransformer)
                .to("log:received-from-active-mq");

        from("activemq:my-activemq-xml-queue")
                .unmarshal()
                .jacksonXml(CurrencyExchange.class)
                .to("log:received-from-activemq-xml");

    }
}
