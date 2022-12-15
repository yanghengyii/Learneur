package edu.whu.learneur.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 用于生成验证码
 */
@Component
public class CodeUtils {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final String unknow = "<unknown>";
    public static final int HASH_INTERARIONS = 1024;
    public static final int SALT_SIZE = 8;

    @Autowired
    private static Environment env;

    /**
     * 加密算法: 生成随记16位salt并经过1024次 sha-1 hash
     * @param plainPassword 原密码
     * @return
     * &#064;memo 此方法应该不会被使用, 本项目密码加密采取前端执行
     */
    public static String encryptPassword(String plainPassword) {
        String plain = EncodesUtils.unescapeHTML(plainPassword);
        byte[] salt = DigestUtils.generateSalt(SALT_SIZE);
        byte[] hashPassword = DigestUtils.sha1(plain.getBytes(), salt, HASH_INTERARIONS);
        return EncodesUtils.encodeHex(salt) + EncodesUtils.encodeHex(hashPassword);
    }

    /**
     * 比较密码
     * @param pwd           密码
     * @param encryptPws    加密后的密码
     * @return
     */
    public static boolean comparePassword(String pwd, String encryptPws) {
        byte[] salt = EncodesUtils.decodeHex(encryptPws.substring(0, 16));
        byte[] hashPassword = DigestUtils.sha1(pwd.getBytes(), salt, HASH_INTERARIONS);
        String enPwd = EncodesUtils.encodeHex(salt) + EncodesUtils.encodeHex(hashPassword);
        return encryptPws.equals(enPwd);
    }

    /**
     * 获取验证码
     * @return
     */
    public static Integer getCode() {
        Integer code = (int) ((Math.random() * 9 + 1) * 100000);
        return code;
    }
}
