package com.example.demo;

import com.example.demo.selfinjection.service.DemoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final DemoServiceImpl demoServiceImpl;

    @Override
    public void run(String... args) throws Exception {

        demoServiceImpl.doSomething();

    }
}
