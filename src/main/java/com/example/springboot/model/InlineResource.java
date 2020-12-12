package com.example.springboot.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Auther: 13213
 * @Date: 2020/12/12 17:55
 * @Description:
 */
@Component
@Data
public class InlineResource {
    private String cid;
    private String path;

    public InlineResource() {
    }

    public InlineResource(String cid, String path) {
        this.cid = cid;
        this.path = path;
    }
}
