package com.parakramaba.producerservice.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.ArrayList;
import java.util.List;

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

//    @Bean
//    public ApplicationRunner runner(KafkaAdmin kafkaAdmin) {
//        return args -> {
//            AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
//            List<NewTopic> topics = new ArrayList<>();
//            topics.add(TopicBuilder.name("tracking.user-activity.create-product").build());
//            topics.add(TopicBuilder.name("tracking.user-activity.update-product").build());
//            topics.add(TopicBuilder.name("tracking.user-activity.delete-product").build());
//            adminClient.createTopics(topics).all().get();
//        };
//    }

}
