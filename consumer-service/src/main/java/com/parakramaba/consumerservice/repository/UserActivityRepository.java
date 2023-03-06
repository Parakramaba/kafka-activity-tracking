package com.parakramaba.consumerservice.repository;

import com.parakramaba.consumerservice.entity.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityRepository extends JpaRepository<UserActivity, String> {
}
