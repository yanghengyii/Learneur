package edu.whu.learneur.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 网课
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("lesson")
public class Lessons implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_lesson", type = IdType.ASSIGN_ID)
    private Long idLesson;

    /**
     * 名称
     */
    private String title;

    /**
     * 简要描述
     */
    private String description;

    /**
     * 链接
     */
    private String link;

    /**
     * 配图
     */
    @TableField(value = "img_path")
    private String imgPath;


}
