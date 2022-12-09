package edu.whu.learneur.resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Project {
    @TableId(value = "id_lesson", type = IdType.AUTO)
    private Long idProject;

    /**
     * 名称
     */
    private String name;

    /**
     * 更新时间
     */
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
    private Integer starGazers;

    /**
     * 复制
     */
    private Integer forks;

    /**
     * 主页
     */
    private String homePage;
    private String language;

    /**
     * readme
     */
    private String readme;

    @TableField(exist = false)
    List<Knowledge> knowledge;

}
