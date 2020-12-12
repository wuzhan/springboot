package com.example.springboot.service;
import com.example.springboot.model.InlineResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.List;
import javax.mail.internet.MimeMessage;

/**
 * @Auther: 13213
 * @Date: 2020/12/12 17:52
 * @Description:
 */
@Service
public class MailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String fromMail;

    /**
     * 发送普通邮件
     *
     * @param toMail  收件方
     * @param subject 标题
     * @param content 邮件内容
     */
    public void sendSimpleMail(String toMail, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setTo(toMail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            sender.send(simpleMailMessage);
            logger.info("发送给:{}简单邮件已经发送。 subject:{}", toMail, subject);
        } catch (Exception e) {
            logger.info("发送给:{}send mail error subject:{}", toMail, subject);
            e.printStackTrace();
        }
    }


    /**
     * 发送带Html格式邮件
     *
     * @param toMail  收件方
     * @param subject 标题
     * @param content 邮件内容
     */
    public void sendHtmlMail(String toMail, String subject, String content) {
//        1、封装数据
        MimeMessage mimeMessage = sender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(toMail);
            mimeMessageHelper.setFrom(fromMail);
            mimeMessageHelper.setText(content, true);
            mimeMessageHelper.setSubject(subject);

//            2、发送邮件
            sender.send(mimeMessage);
            logger.info("发送给:{}html邮件已经发送。 subject:{}", toMail, subject);
        } catch (Exception e) {
            logger.info("发送给:{}html send mail error subject:{}", toMail, subject);
            e.printStackTrace();
        }
    }


    /**
     * 发送静态资源（一般是图片）的邮件
     *
     * @param
     * @param subject
     * @param content 邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:image\" >
     * @param
     */
    public void sendHtmlPhotoMail(String to, String subject, String content, List<InlineResource> resourceist) {

//            1、编写发送的数据
        MimeMessage message = sender.createMimeMessage();
//            1-1、添加html数据
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromMail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
//                1-2、添加图片数据
            for (InlineResource inlineResource : resourceist) {
//                    图片的路径
                FileSystemResource res = new FileSystemResource(new File(inlineResource.getPath()));
//                    将html中的图片名称替换为相应的图片路径
                helper.addInline(inlineResource.getCid(), res);
            }

//                2、发送邮件
            sender.send(message);
            logger.info("嵌入静态资源的邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }


    /**
     * 发送带附件邮件
     *
     * @param toMail
     * @param subject
     * @param content
     */
    public void sendFileMail(String toMail, String subject, String content, String filePath) {
//        1、编写发送的数据
        MimeMessage message = sender.createMimeMessage();
//        1-1、添加html数据
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromMail);
            helper.setTo(toMail);
            helper.setSubject(subject);
            helper.setText(content, true);
//            1-2、添加图片数据
//            获取图片数据
            FileSystemResource file = new FileSystemResource(new File(filePath));
//            获取图片的名称
            String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
//            添加数据
            helper.addAttachment(fileName, file);

//            2、发送邮件
            sender.send(message);
            logger.info("发送给:{}带附件的邮件已经发送。",toMail);
        } catch (Exception e) {
            logger.error("发送给:{}带附件的邮件时发生异常！", toMail);
            e.printStackTrace();
        }
    }
}
