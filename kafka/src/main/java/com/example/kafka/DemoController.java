package com.example.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "test";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/p/{message}")
    public String publishMessage(@PathVariable String message) {
        kafkaTemplate.send(TOPIC, message);
        log.info("adding message to queue");
        return "Published event";
    }

}
