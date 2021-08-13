package com.bhavye.configs;

import com.bhavye.models.MyMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.BatchLoggingErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class MyConsumerConfig {

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value("${kafka.consumer.group.id}")
    private String groupId;

    @Value("${kafka.consumer.max.poll.records}")
    private String maxPollRecords;

    @Bean
    public Map<String, Object> consumerConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        return config;
    }

    @Bean
    public ConsumerFactory<String, MyMessage> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), null, new JsonDeserializer<>(MyMessage.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MyMessage> kafkaListenerContainerFactory () {
        ConcurrentKafkaListenerContainerFactory<String, MyMessage> listener = new ConcurrentKafkaListenerContainerFactory<>();
        listener.setConsumerFactory(consumerFactory());
        listener.setBatchListener(true);
        listener.setBatchErrorHandler(new BatchLoggingErrorHandler());
        return listener;
    }

}
