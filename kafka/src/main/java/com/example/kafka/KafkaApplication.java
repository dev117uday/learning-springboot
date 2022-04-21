package com.example.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication {

    private final static Logger logger = LoggerFactory.getLogger(KafkaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);

        String TOPIC = "test";
        String key = "key";
        String value = "value";

        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> kafkaProducer = new KafkaProducer<>(props);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(TOPIC, key, value);

        try {
            kafkaProducer.send(producerRecord).get();
        } catch (Exception e) {
            logger.error("error in producing message to kafka");
        } finally {
            kafkaProducer.close();
        }

        logger.info("message produced to kafka");
    }

}
