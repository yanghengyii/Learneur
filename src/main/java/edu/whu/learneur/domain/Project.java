package edu.whu.learneur.domain;

import java.time.LocalDate;

public class Project {
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

    /**
     * 语言
     */
    private String language;

    /**
     * readme
     */
    private String readme;
}
