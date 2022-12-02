package edu.whu.learneur.dto;

import lombok.Data;

@Data
public class ChangeEmailDTO {
    /* 用户名 */
    private String username;
    /* 验证码 */
    private String code;
    /* 邮箱地址 */
    private String email;
}
