package edu.whu.learneur.neo4j.dto;

import edu.whu.learneur.neo4j.domain.Knowledge;
import edu.whu.learneur.neo4j.domain.Relation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 *     知识图谱数据库节点和关系dto类
 *     用于存储知识图谱数据库中的节点和关系
 * </p>
 * @author Geraltigas
 * @since 2022-12-10
 * @version 1.0
 */
@Setter
@Getter
public class KnowledgeAndRelations {

/**
     * <p>
     *     节点
     * </p>
     */
    List<Knowledge> knowledges;

    /**
     * <p>
     *     关系
     * </p>
     */
    List<Relation> relations;
    @Override
    public String toString() {
        return "KnowledgeAndRelations{" +
                "knowledges=" + knowledges +
                ", relation=" + relations;
    }
}
