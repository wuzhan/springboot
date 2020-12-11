package com.example.springboot.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Auther: 13213
 * @Date: 2020/12/4 17:57
 * @Description:
 */
@Component
public class HelloCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("HelloCommandLineRunner: hello spring boot");
    }
}
