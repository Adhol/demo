package com.example.demo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ExecutionTimeAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    @Around("@annotation(com.example.demo.annotations.Loggable)")
    public Object Loggable(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        StopWatch stopWatch = new StopWatch(className + "-->" + methodName);
        stopWatch.start(methodName);
        Object proceed = jp.proceed();
        stopWatch.stop();
        LOGGER.info("StopWatch '" + stopWatch.getId() + "': running time = " + stopWatch.getTotalTimeMillis() + " ms");
        return proceed;
    }
}
