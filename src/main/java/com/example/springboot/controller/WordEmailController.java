package com.example.springboot.controller;
import com.example.springboot.model.InlineResource;
import com.example.springboot.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @Auther: 13213
 * @Date: 2020/12/12 17:50
 * @Description:
 */
@RestController
public class WordEmailController {
    @Autowired
    private MailService mailService;

    /**
     * 测试普通邮件调用
     */
    @RequestMapping(value = "/simpleEmail", method = {RequestMethod.POST})
    public void sendSimpleEmail() {
        mailService.sendSimpleMail("wuzhan@csii.com.cn", "这是一个测试邮件", "这是一个测试邮件");
    }

    /**
     * 测试Html带图片
     */
    @RequestMapping(value = "/htmlPotoEmail", method = {RequestMethod.POST})
    public void sendHtmlPotoEmail() {
//        1、创建html格式的数据
        String html = "<!DOCTYPE html>"+
                "<html lang=\"en\">"+
                "<head>"+
                "    <meta charset=\"UTF-8\">"+
                "    <title>Title</title>"+
                "</head>"+
                "<body>"+
                "这是我发的第一封Java邮件"+
                "<a href=\'http://www.baidu.com\'>【百度一下】</a><br>"+
                "<img src=\"cid:image1\">"+
                "</body>"+
                "</html>";
//        2、封装html中的图片数据
        List<InlineResource> list = new ArrayList<>();
//        2-1获取图片的地址
        String path = WordEmailController.class.getClassLoader().getResource("static/wenbo.jpg").getPath();
//        2-2、将图片的地址封装到实体类中
        InlineResource resource = new InlineResource("image1", path);
//        InlineResource resource2 = new InlineResource("image2", path);
//        list.add(resource2);
        list.add(resource);

//        3、发送邮件
        mailService.sendHtmlPhotoMail("wuzhan@csii.com.cn", "这是一个测试邮件", html, list);

    }

    /**
     * 测试附件调用
     */
    @RequestMapping(value = "/fileEmail", method = {RequestMethod.POST})
    public void sendFileEmail() {
//        1、写html格式内容
        String html = "<!DOCTYPE html>"+
                "<html lang=\"en\">"+
                "<head>"+
                "    <meta charset=\"UTF-8\">"+
                "    <title>Title</title>"+
                "</head>"+
                "<body>"+
                "这是我发的第一封Java邮件"+
                "<a href=\'http://www.baidu.com\'>【百度一下】</a><br>"+
                "</body>"+
                "</html>";
//        2、获取图片所在的路径
        String path = this.getClass().getClassLoader().getResource("static/wenbo.jpg").getPath();
//        3、发送邮件
        mailService.sendFileMail("wuzhan@csii.com.cn", "这是一个测试邮件", html, path);
    }

}
