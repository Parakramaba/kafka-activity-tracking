package com.parakramaba.producerservice.aspect;

import com.parakramaba.producerservice.kafka.ProductProducer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class ProductAspect {

    private static final String serviceName = "Producer Service";

    @Autowired
    private ProductProducer productProducer;

    @Pointcut(value = "execution(* com.parakramaba.producerservice.controller.*.createProduct(..))")
    private void createProduct() { }

    @Around("createProduct()")
    private Object aroundCreateProduct(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        final String methodName = proceedingJoinPoint.getSignature().getName();
        final Long executionTime = stopWatch.getTotalTimeMillis();
        productProducer.putActivityToKafkaStream(serviceName, methodName, "Make a reservation", executionTime);
        return result;
    }
}
