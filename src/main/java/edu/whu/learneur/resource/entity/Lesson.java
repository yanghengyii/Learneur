package edu.whu.learneur.crawler.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_lesson", type = IdType.ASSIGN_ID)
    private Long id;

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
