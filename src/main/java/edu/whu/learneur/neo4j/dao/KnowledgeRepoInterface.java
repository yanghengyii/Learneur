package edu.whu.learneur.neo4j.dao;

import edu.whu.learneur.neo4j.domain.Knowledge;
import edu.whu.learneur.neo4j.domain.Relation;
import org.neo4j.driver.Record;

import java.util.List;
import java.util.Optional;

public interface KnowledgeRepoInterface {
    Optional<List<Relation>> updateRelationById(Long relationId, String type, String description);

    Optional<Relation> addRelation(Long knowledgeId, Long relatedId, String type, String description);

    Optional<Knowledge> updateTagById(Knowledge knowledge, Long id);

    Optional<Knowledge> deleteById(Long id);

    Optional<Relation> deleteRelationById(Long start, Long end);

    Optional<List<Relation>> findRelationByNameAndType(String name, String type);

    Optional<List<Relation>> findRelationByName(String name, Integer depth);

    Optional<List<Knowledge>> findAllRelated(String name);
}
