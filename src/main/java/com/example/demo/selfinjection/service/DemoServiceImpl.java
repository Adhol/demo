package com.example.demo.selfinjection.service;

import com.example.demo.annotations.InjectRandomString;
import com.example.demo.annotations.Loggable;
import com.example.demo.annotations.SelfInjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl{

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

    @InjectRandomString
    private String randomStringWithDefaultParams;

    @InjectRandomString(minLength = 7, maxLength = 8)
    private String randomStringWithParams;

    @Autowired
    private DemoServiceImpl selfWithAutowired;

    @SelfInjection
    private DemoServiceImpl selfWithSelfInjectionBeanPostProcessor;

    private final DemoServiceImpl selfWithLazy;

    public DemoServiceImpl(@Lazy DemoServiceImpl selfWithLazy) {
        this.selfWithLazy = selfWithLazy;
    }

    @Loggable
    public void doSomething() throws InterruptedException {
        LOGGER.info(randomStringWithDefaultParams + " with length " + randomStringWithDefaultParams.length());
        selfWithAutowired.doNothing();
        selfWithLazy.doNothing();

        // self injection происходит, но логгирование вложенного метода нет
        selfWithSelfInjectionBeanPostProcessor.doNothing();
        LOGGER.info(randomStringWithParams + " with length " + randomStringWithParams.length());
    }

    @Loggable
    public void doNothing() throws InterruptedException {
        System.out.println("i was called");
        Thread.sleep(3000);
    }
}
