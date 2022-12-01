package edu.whu.learneur.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 项目
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Projects implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_project", type = IdType.ASSIGN_ID)
    private Long idProject;

    /**
     * 名称
     */
    private String name;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDate updateTime;

    /**
     * 跳转链接
     */
    private String link;

    /**
     * 简要信息
     */
    private String description;

    /**
     * 收藏
     */
    @TableField(value = "star_gazers")
    private Integer starGazers;

    /**
     * 复制
     */
    private Integer forks;

    /**
     * 主页
     */
    @TableField(value = "home_page")
    private String homePage;

    /**
     * readme
     */
    private String readme;


}
