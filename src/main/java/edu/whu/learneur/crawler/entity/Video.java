package edu.whu.learneur.crawler.entity;

import lombok.Data;

// bilibili video
@Data
public class Video {
    private String author;
    private String BVid;
    private String length;
    private String pic;
    private String description;
    private String title;
}
