package edu.whu.learneur.neo4j.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Data
@Node("knowledge")
@NoArgsConstructor
public class Knowledge {
    @Id
    @GeneratedValue
    private Long id;

    @Property("name")
    private String name;

    @Property("description")
    private String description;

    @Property("foreign_id")
    @JsonIgnore
    private Long foreignId;

    @Relationship(direction = Relationship.Direction.OUTGOING,type = "preKnowledge")
    @JsonIgnore
    private List<Relation> preKnowledges;

    @Relationship(direction = Relationship.Direction.OUTGOING,type = "include")
    @JsonIgnore
    private List<Relation> includes;

    @Relationship(direction = Relationship.Direction.OUTGOING,type = "associated")
    @JsonIgnore
    private List<Relation> associateds;

    @Relationship(direction = Relationship.Direction.OUTGOING,type = "common")
    @JsonIgnore
    private List<Relation> commons;

    public Knowledge(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", foreignId=" + foreignId +
                '}';
    }
}
