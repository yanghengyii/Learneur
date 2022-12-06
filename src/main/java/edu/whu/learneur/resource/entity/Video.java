package edu.whu.learneur.resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

// bilibili video
@Data
public class Video {
    @TableId(value = "id_video",type = IdType.AUTO)
    private Long id;

    private String author;
    private String BVid;
    private String length;
    private String pic;
    private String description;
    private String title;
}
