package com.example.springboot.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: 13213
 * @Date: 2020/11/20 17:41
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "student")
public class StudentProperties {
    private String name;
    private Integer age;
}
