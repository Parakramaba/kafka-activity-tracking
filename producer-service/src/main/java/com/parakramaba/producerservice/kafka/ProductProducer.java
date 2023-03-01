package com.parakramaba.producerservice.kafka;

import com.parakramaba.producerservice.dto.UserActivityDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductProducer {

    @Autowired
    private KafkaTemplate<String, UserActivityDto> kafkaTemplate;

    @Autowired
    @Qualifier("createProduct")
    private NewTopic topic;

    public void putActivityToKafkaStream(final String serviceName, final String methodName,
                                         final String description,
                                         final Long executionTime) {

        UserActivityDto userActivity = new UserActivityDto();
        userActivity.setServiceName(serviceName);
        userActivity.setMethodName(methodName);
        userActivity.setDescription(description);
        userActivity.setExecutionTime(executionTime);
        userActivity.setDateTimeOfActivity(LocalDateTime.now());

        // Create kafka message
        Message<UserActivityDto> message = MessageBuilder
                .withPayload(userActivity)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
