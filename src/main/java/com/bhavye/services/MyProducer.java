package com.bhavye.services;

import com.bhavye.models.MyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MyProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyProducer.class);

    @Autowired
    private KafkaTemplate<String, MyMessage> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topic;

    public void send(MyMessage msg) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaHeaders.TOPIC, topic);
        kafkaTemplate.send(new GenericMessage<>(msg, headers));
        LOGGER.info("Sent message='{}' to topic='{}'", msg, topic);
    }

}
