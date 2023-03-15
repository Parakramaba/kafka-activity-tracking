package com.parakramaba.consumerservice.kafka;

import com.parakramaba.consumerservice.dto.UserActivityDto;
import com.parakramaba.consumerservice.entity.UserActivity;
import com.parakramaba.consumerservice.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductConsumer {

    private static final String topicCreateProduct = "tracking.user-activity.create-product";

    @Autowired
    private UserActivityRepository userActivityRepository;

    @KafkaListener(topics = topicCreateProduct, groupId = "${spring.kafka.consumer.group-id}")
    public void saveUserActivity(UserActivityDto userActivityDto) {

        UserActivity userActivity = new UserActivity();
        String userActivityId = UUID.randomUUID().toString();
        userActivity.setId(userActivityId);
        userActivity.setServiceName(userActivityDto.getServiceName());
        userActivity.setMethodName(userActivityDto.getMethodName());
        userActivity.setDescription(userActivityDto.getDescription());
        userActivity.setExecutionTime(userActivityDto.getExecutionTime());
        userActivity.setDateTimeOfActivity(userActivityDto.getDateTimeOfActivity());
        userActivityRepository.save(userActivity);
    }
}
