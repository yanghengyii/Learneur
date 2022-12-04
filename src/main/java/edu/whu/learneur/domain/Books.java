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
 * 书籍
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("books")
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_book", type = IdType.ASSIGN_ID)
    private Long idBook;

    /**
     * 书籍名称
     */
    private String title;

    /**
     * 配图
     */
    @TableField(value = "img_path")
    private String imgPath;

    /**
     * 书籍本地路径
     */
    private String path;

    /**
     * 书籍作者
     */
    private String author;

    /**
     * 出版商
     */
    private String publisher;

    /**
     * 语言
     */
    private String language;

    /**
     * 下载链接
     */
    @TableField(value = "download_url")
    private String downloadUrl;

    @TableField(exist = false)
    private Long idResource;
}
