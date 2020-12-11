package com.example.springboot.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.Ordered;

/**
 * @Auther: 13213
 * @Date: 2020/12/4 17:08
 * @Description:
 */
public class AfterContextClosedEventListener implements ApplicationListener<ContextClosedEvent>, Ordered {
    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        System.out.println("zhan -- AfterContextClosedEventr: " + contextClosedEvent.getApplicationContext().getId());
    }

    @Override
    public int getOrder() {
        //数值越小，优先级越高
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
