package edu.whu.learneur.neo4j.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.neo4j.core.schema.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@RelationshipProperties
public class Relation {
    @Id
    @GeneratedValue
    private Long id;

    @Property("type")
    private String type;

    @Property("description")
    private String description;
    @TargetNode
    @JsonIgnore
    private Knowledge knowledge;

    private Long start;

    private Long end;

    public Relation(String type, String description, Long id, Long start, Long end) {
        this.type = type;
        this.description = description;
        this.start = start;
        this.end = end;
        this.id = id;
    }
}
