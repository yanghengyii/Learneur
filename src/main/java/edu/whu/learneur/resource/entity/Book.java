package edu.whu.learneur.resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Book {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_book", type = IdType.AUTO)
    private Long idBook;

    /**
     * 书籍名称
     */
    private String title;

    /**
     * 配图
     */
    @TableField(value = "cover_url")
    private String coverUrl;

//    /**
//     * 书籍本地路径
//     */
//    private String path;

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
    private String link;

    private String fileSize;
    private String fileType;

    private String publishDate;

    @TableField(exist = false)
    List<Knowledge> knowledge;
}
