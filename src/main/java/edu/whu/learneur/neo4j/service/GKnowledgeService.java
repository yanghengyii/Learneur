package edu.whu.learneur.neo4j.service;

import edu.whu.learneur.neo4j.domain.Knowledge;
import edu.whu.learneur.neo4j.dto.KnowledgeAndRelations;

import java.util.List;
import java.util.Map;

public interface GKnowledgeService {
    Knowledge getTagById(Long id);
    List<Knowledge> getTagByName(String name);
    Knowledge addTag(Knowledge knowledge);
    Map<String,Object>[] addRelation(Long knowledgeId, Long relatedId, String type, String description);

    Map<String,Object>[] updateTagById(Knowledge knowledge, Long id);

    Map<String,Object>[] updateRelationById(Long relationId, String type, String description);

    Map<String,Object>[] deleteTagById(Long id);
    Map<String,Object>[] deleteRelationById(Long id);

    KnowledgeAndRelations getGraphByName(String name);
}