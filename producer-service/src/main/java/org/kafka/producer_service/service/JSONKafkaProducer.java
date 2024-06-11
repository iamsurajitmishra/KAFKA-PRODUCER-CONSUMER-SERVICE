package org.kafka.producer_service.service;

import org.kafka.producer_service.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JSONKafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONKafkaProducer.class);
    private KafkaTemplate<String, User> kafkaTemplate;
    public JSONKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User data, String topic) {
        LOGGER.info(String.format("Message sent-> %s",data.toString()));
        Message<User> message = MessageBuilder.withPayload(data)
                .setHeader(KafkaHeaders.TOPIC,topic)
                .build();
       kafkaTemplate.send(message);
    }

}
