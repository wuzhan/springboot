package com.example.springboot.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @Auther: 13213
 * @Date: 2020/12/4 16:37
 * @Description:
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("wu -- ConfigurableApplicationContext.id - " + configurableApplicationContext.getId());
    }
}
