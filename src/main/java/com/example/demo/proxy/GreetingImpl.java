package com.example.demo.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingImpl implements Greeting{

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingImpl.class);

    @Override
    public void hello() {

        LOGGER.info("Hello");

    }
}
