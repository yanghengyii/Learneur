package edu.whu.learneur.crawler.entity;

import lombok.Data;

@Data
public class Book {
    private String title;
    private String author;
    private String publisher;
    private String publishDate;
    private String[] tags;
    private String language;
    private String fileType;
    private String fileSize;
    private String coverUrl;
}
