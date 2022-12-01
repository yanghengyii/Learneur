package edu.whu.learneur.crawler.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Information implements Serializable {
    private Long id;

    String name;

    String link;

    String summary;

    String type;

    String img;
}
