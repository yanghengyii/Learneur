package edu.whu.learneur.service.impl;

import edu.whu.learneur.service.IEMailService;
import edu.whu.learneur.service.IUsersService;
import edu.whu.learneur.utils.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StopWatch;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class EmailServiceImpl implements IEMailService {
    @Resource
    private JavaMailSenderImpl sender;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IUsersService usersService;

    @Resource
    private TemplateEngine templateEngine;

    /* 设置发件人信息 */
    @Value("${spring.mail.host}")
    private String SENDER_HOST;

    @Value("${spring.mail.port}")
    private String SENDER_PORT;

    @Value("${spring.mail.username}")
    private String SENDER_USERNAME;

    @Value("${spring.mail.password}")
    private String SENDER_PASSWORD;

    @Value("${resource.domain}")
    private String BASE_URL;

    @Override
    public boolean sendEmailCode(String email) throws MessagingException {
        return sendVerifiedCode(email, 1);
    }

    @Override
    public boolean sendForgetPasswordEmail(String email) throws MessagingException {
        return sendVerifiedCode(email, 2);
    }

    /**
     * 发送验证码
     * @param toUsername    收件人
     * @param type          1:注册用; 2:忘记密码用
     * @return
     * @throws MessagingException
     */
    private boolean sendVerifiedCode(String toUsername, int type) throws MessagingException {
        Properties properties = new Properties();

        // SMTP邮件发送身份验证
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.host", SENDER_HOST);
        properties.put("mail.smtp.port", SENDER_PORT);

        // ssl配置
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.port", SENDER_PORT);

        // 发件人账号
        properties.put("mail.user", SENDER_USERNAME);
        properties.put("mail.password", SENDER_PASSWORD);

        // 进入分支
        if(type == 1) {
            /* 获取验证码 */
            Integer code = CodeUtils.getCode();
            redisTemplate.boundValueOps(toUsername).set(code.toString(), 5, TimeUnit.MINUTES);

            String subject = "注册 | 验证码操作";

            String tempatePath = "template/mail/registerTemplate.html";
            Map<String, Object> variables = new HashMap<>();
            variables.put("code", code);
            String emailText = getTemplateEmail(
                    SENDER_USERNAME, new String[]{toUsername}, new String[]{},
                    subject, tempatePath, variables
            );

            sendMiMEmail(
                    SENDER_USERNAME, new String[]{toUsername}, new String[]{},
                    subject, emailText, true, null
            );
            return true;
        } else if(type == 2) {
            String code = CodeUtils.encryptPassword(toUsername);
            String url = BASE_URL + "/forget-pwd?code=" + code;
            redisTemplate.boundValueOps(code).set(toUsername, 15, TimeUnit.MINUTES);

            String subject = "找回密码 | 验证码操作";

            String tempatePath = "template/mail/forgetPasswordTemplate.html";
            Map<String, Object> variables = new HashMap<>();
            variables.put("url", url);

            String emailText = getTemplateEmail(
                    SENDER_USERNAME, new String[]{toUsername}, new String[]{},
                    subject, tempatePath, variables
            );

            sendMiMEmail(
                    SENDER_USERNAME, new String[]{toUsername}, new String[]{},
                    subject, emailText, true, null
            );
            return true;

        }
        return false;
    }

    /**
     * 生成thymeleafTemplate邮件
     * @param fromUser                  发送人邮件
     * @param toUsers                   接收人
     * @param copyUsers                 抄送人
     * @param subject                   标题
     * @param thymeleafTemplatePath     邮件模板
     * @param thymeleafTemplateVariable 邮件模板变量集合
     * @return                          生成的邮件
     */
    private String getTemplateEmail(
            String fromUser, String[] toUsers, String[] copyUsers,
            String subject, String thymeleafTemplatePath,
            Map<String, Object> thymeleafTemplateVariable
    ) {
        String emailText = "";
        if(Objects.nonNull(thymeleafTemplatePath) && thymeleafTemplateVariable.size() > 0) {
            Context context = new Context();
            thymeleafTemplateVariable.forEach(context::setVariable);
            emailText = templateEngine.process(thymeleafTemplatePath, context);
        }
        return emailText;
    }

    /**
     * 发送MIME邮件
     * @param fromUser      发件人
     * @param toUsers       收件人
     * @param copyUsers     抄送人
     * @param subject       标题
     * @param emailText     主体
     * @param isHTML        是否为HTML
     * @param attachments   附件路径
     * @throws MessagingException
     */
    private void sendMiMEmail(
            String fromUser, String[] toUsers, String[] copyUsers,
            String subject, String emailText, boolean isHTML, String[] attachments
    ) throws MessagingException {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromUser);
        helper.setTo(toUsers);
        helper.setCc(copyUsers);
        helper.setSubject(subject);
        helper.setText(emailText);

        /* 添加附件 */
        if(!Objects.nonNull(attachments) && attachments.length > 0) {
            for (String attachment : attachments) {
                File file = new File(attachment);
                if(file.exists()) {
                    String attachmentFile = attachment.substring(attachment.lastIndexOf(File.separator));
                    long size = file.length();
                    if(size > 1024 * 1024) {
                        String msg = String.format(
                                "Size of a single attachment cannot be over 1MB. File [%s] Size: [%s]",
                                attachmentFile, file.length()
                        );
                        throw new RuntimeException(msg);
                    }
                    else  {
                        FileSystemResource fileSystemResource = new FileSystemResource(file);
                        helper.addAttachment(attachmentFile, fileSystemResource);
                    }
                }
            }
        }
        sender.send(message);
        stopWatch.stop();
    }
}
