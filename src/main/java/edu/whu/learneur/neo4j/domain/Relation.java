package edu.whu.learneur.neo4j.domain;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Data
@RelationshipProperties
public class Relation {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String description;
    @TargetNode
    private Knowledge knowledge;

    public Relation(String type, String description, Knowledge knowledge) {
        this.type = type;
        this.description = description;
        this.knowledge = knowledge;
    }
}
