package com.example.demo.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingProxy implements Greeting{

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingProxy.class);

    private static Greeting greeting;

    @Override
    public void hello() {
        if (greeting == null) {
            greeting = new GreetingImpl();
        }
        LOGGER.warn("<- I proxy of " + greeting.getClass() + "->");
        greeting.hello();
        LOGGER.warn("<------------------------------------------------------->");
    }
}
