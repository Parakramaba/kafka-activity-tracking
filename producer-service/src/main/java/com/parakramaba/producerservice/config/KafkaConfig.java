package com.parakramaba.producerservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean(name = "createProduct")
    public NewTopic createProduct() {
        return TopicBuilder
                .name("tracking.user-activity.create-product")
                .build();
    }

    @Bean(name = "updateProduct")
    public NewTopic updateProduct() {
        return TopicBuilder
                .name("tracking.user-activity.update-product")
                .build();
    }

}
