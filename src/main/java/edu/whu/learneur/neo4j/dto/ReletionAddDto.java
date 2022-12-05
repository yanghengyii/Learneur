package edu.whu.learneur.neo4j.dto;

import lombok.Data;

@Data
public class ReletionAddDto {
    private Long knowledgeId;
    private Long relatedId;
    private String type;
    private String description;
}
