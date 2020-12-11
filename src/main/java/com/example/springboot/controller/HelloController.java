package com.example.springboot.controller;

import com.example.springboot.model.StudentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 13213
 * @Date: 2020/11/20 17:17
 * @Description:
 */
@RestController
public class HelloController {
//    @Value("${name}")
//    private String name;
//
//    @Value("${age}")
//    private Integer age;

    @Autowired
    private StudentProperties studentProperties;

    @RequestMapping("/hello")
    public String hello(){
        return studentProperties.getName() + studentProperties.getAge();
    }

}
