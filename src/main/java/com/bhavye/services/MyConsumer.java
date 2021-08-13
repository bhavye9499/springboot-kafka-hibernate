package com.bhavye.services;

import com.bhavye.models.MyBatch;
import com.bhavye.models.MyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class MyConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyConsumer.class);

    @Autowired
    private MyBatchDao myBatchDao;

    @KafkaListener(groupId = "${kafka.consumer.group.id}", topics = "${kafka.topic.name}")
    public void receive(@Payload List<MyMessage> messages) {
        ArrayList<Integer> values = new ArrayList<>();
        LOGGER.info("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        LOGGER.info("Beginning to consume batch messages");
        for (MyMessage message : messages) {
            values.add(message.getValue());
            LOGGER.info("Received message='{}'", message);
        }
        LOGGER.info("All batch messages received");
        MyBatch batch = new MyBatch();
        int sum = values.stream().mapToInt(Integer::intValue).sum();
        batch.setSum(sum);
        batch.setAverage((double) sum / values.size());
        batch.setMaximum(Collections.max(values));
        batch.setMinimum(Collections.min(values));
        myBatchDao.createBatch(batch);
        LOGGER.info("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    }

}
