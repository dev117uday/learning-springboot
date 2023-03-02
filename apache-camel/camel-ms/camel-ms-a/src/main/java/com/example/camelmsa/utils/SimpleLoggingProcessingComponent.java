package com.example.camelmsa.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleLoggingProcessingComponent implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("SimpleLoggingProcessingComponent : {}", exchange.getMessage().getBody());
    }
}
