package com.example.springboot;
import com.example.springboot.model.StudentProperties;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootApplicationTests {

    //TODO 此处可以注入需要测试的服务接口
    @Autowired
    private StudentProperties studentProperties;

    @Test
    public void contextLoads() {
        System.out.println("wuwu");
        System.out.println(studentProperties.getName() + studentProperties.getAge());
        //TODO 此处调用接口方法
    }

}
