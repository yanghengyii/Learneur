package edu.whu.learneur.service;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * @since  2022-12-01
 */
public interface IEMailService {
    /**
     * 发送验证码邮件
     * @param email 收件人地址
     * @return      操作结果
     * @throws MessagingException
     */
    boolean sendEmailCode(String email) throws MessagingException;

    /**
     * 发送忘记密码邮件
     * @param email 收件人地址
     * @return      操作结果
     * @throws MessagingException
     */
    boolean sendForgetPasswordEmail(String email) throws MessagingException;
}
