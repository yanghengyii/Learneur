package edu.whu.learneur.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试类, 此处为邮件发送测试类
 */
@SpringBootTest
@Transactional
public class EmailServiceTest {
    @Autowired
    private IEMailService mailService;

    @Test
    public void sendEmailCodeTest() throws MessagingException {
        boolean res = mailService.sendEmailCode("3137729452@qq.com");
        assertTrue(res);

    }

    @Test
    public void sendEmailForgetPasswordTest() throws MessagingException {
        boolean res = mailService.sendForgetPasswordEmail("3137729452@qq.com");
        assertTrue(res);
    }
}
