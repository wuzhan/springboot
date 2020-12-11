package com.example.springboot.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @Auther: 13213
 * @Date: 2020/12/4 17:05
 * @Description:
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        System.out.println("wuwu -- ContextClosedEvent: " + contextClosedEvent.getApplicationContext().getId());
    }
}
