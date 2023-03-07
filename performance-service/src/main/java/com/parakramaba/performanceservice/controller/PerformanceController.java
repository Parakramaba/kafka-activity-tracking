package com.parakramaba.performanceservice.controller;

import com.parakramaba.performanceservice.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @PostMapping("/check/{noOfCalls}")
    public String testKafkaPerformance(final @PathVariable("noOfCalls") int noOfCalls) {
        return performanceService.testKafkaPerformance(noOfCalls);
    }
}
