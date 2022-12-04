package edu.whu.learneur.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像路径
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 状态
     */
    private String online;

    /**
     * 创建时间
     */
    @TableField(value = "register_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerTime;

    /**
     * 最后登录时间
     */
    @TableField(value = "last_login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    /**
     * 个人简介
     */
    private String description;

    /**
     * 角色
     */
    @TableField(value = "id_role")
    private Long idRole;


}
