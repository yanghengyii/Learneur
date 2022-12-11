package edu.whu.learneur.neo4j.dto;

import edu.whu.learneur.neo4j.domain.Knowledge;
import edu.whu.learneur.neo4j.domain.Relation;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class KnowledgeAndRelations {
    List<Knowledge> knowledges;
    List<Relation> relations;
    @Override
    public String toString() {
        return "KnowledgeAndRelations{" +
                "knowledges=" + knowledges +
                ", relation=" + relations;
    }
}
