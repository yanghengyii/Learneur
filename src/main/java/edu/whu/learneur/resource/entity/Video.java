package edu.whu.learneur.resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

// bilibili video
@Data
public class Video {
    @TableId(value = "id_video",type = IdType.AUTO)
    private Long id;

    private String author;
    private String bvid;
    private String length;
    private String pic;
    private String description;
    private String title;

    @TableField(exist = false)
    List<Knowledge> knowledge;
}
