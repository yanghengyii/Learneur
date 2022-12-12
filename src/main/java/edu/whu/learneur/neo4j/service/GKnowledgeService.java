package edu.whu.learneur.neo4j.service;

import edu.whu.learneur.neo4j.domain.Knowledge;
import edu.whu.learneur.neo4j.domain.Relation;
import edu.whu.learneur.neo4j.dto.KnowledgeAndRelations;

import java.util.List;
import java.util.Map;

public interface GKnowledgeService {
    Knowledge getTagById(Long id);
    List<Knowledge> getTagByName(String name);
    Knowledge addTag(Knowledge knowledge);
    Relation addRelation(Long knowledgeId, Long relatedId, String type, String description);

    Relation addRelationByNames(String name1, String name2, String type);

    Knowledge updateTagById(Knowledge knowledge, Long id);

    List<Relation> updateRelationById(Long relationId, String type, String description);

    Knowledge deleteTagById(Long id);
    Relation deleteRelationById(Long id,Long end);

    KnowledgeAndRelations getGraphByName(String name);

    List<Knowledge> getFirst25Knowledge();
}