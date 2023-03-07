package com.parakramaba.performanceservice.service;

import com.parakramaba.performanceservice.dto.ProductDetailsDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("PerformanceService")
public class PerformanceService {

    private RestTemplate restTemplate = new RestTemplate();

    private ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    private final StopWatch stopWatch = new StopWatch();

    public String testKafkaPerformance(final int noOfCalls) {

        List<ProductDetailsDto> products = IntStream
                .rangeClosed(1, noOfCalls)
                .mapToObj(i -> new ProductDetailsDto("Product " + i, "Brand " + i
                        , threadLocalRandom.nextLong(5, 1000)))
                .collect(Collectors.toList());
        stopWatch.start();
        for (ProductDetailsDto product
                : products) {
            // Will be needed API Gateway and Discovery Server to real world Microservice Communication.
            restTemplate.postForObject("http://localhost:8080/api/v1/producer/products/new", product, String.class);
        }
        stopWatch.stop();
        return "Time take to execute " + noOfCalls + " API calls is " + stopWatch.getTotalTimeMillis() + "ms";
    }
}
