package com.example.demo.config;

import com.example.demo.annotations.InjectRandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;


@Component
public class InjectRandomStringBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(InjectRandomStringBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectRandomString.class)) {
                field.setAccessible(true);
                InjectRandomString annotation = field.getAnnotation(InjectRandomString.class);
                ReflectionUtils.setField(field, bean, getRandomString(annotation.minLength(), annotation.maxLength()));
                LOGGER.info("String was injected");
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private String getRandomString(int minLength, int maxLength) {
        Random random = new Random();
        int leftLimit = 97; // 'a'
        int rightLimit = 122; // 'z'

        int length = (random.nextInt(maxLength - minLength + 1)) + minLength;

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
