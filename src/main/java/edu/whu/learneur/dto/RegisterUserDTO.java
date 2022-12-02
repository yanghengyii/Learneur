package edu.whu.learneur.dto;

import lombok.Data;

@Data
public class RegisterUserDTO {
    /* 用户名 */
    private String username;
    /* 密码 */
    private String password;
    /* 注册邮箱 */
    private String email;
    /* 验证码 */
    private String verificationCode;
}
