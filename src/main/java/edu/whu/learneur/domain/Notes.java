package edu.whu.learneur.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学习笔记
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("note")
public class Notes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "note_id", type = IdType.AUTO)
    private Long noteId;

    /**
     * 文章标题
     */
    @TableField(value = "note_title")
    private String noteTitle;

    /**
     * 文章作者id
     */
    @TableField(value = "note_author_id")
    private Long noteAuthorId;

    /**
     * 文章知识点
     */
    @TableField("note_KP")
    private String noteKp;

    /**
     * 浏览总数
     */
    @TableField(value = "note_view_count")
    private Integer noteViewCount;

    /**
     * 预览内容
     */
    @TableField(value = "note_preview_content")
    private String notePreviewContent;

    /**
     * 评论总数
     */
    @TableField(value = "note_comment_count")
    private Integer noteCommentCount;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    /**
     * 点赞总数
     */
    @TableField(value = "note_thumbs_up_count")
    private Integer noteThumbsUpCount;

    /**
     * 资源
     */
    @TableField(value = "id_resources")
    private Long idResources;

    /**
     * 评论内容
     */
    @TableField(value = "note_content")
    private String noteContent;

    @Version
    private Integer version;
}
