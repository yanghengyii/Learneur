package edu.whu.learneur.elasticsearch.dao;

import edu.whu.learneur.elasticsearch.entity.ResourceEs;

import java.util.List;

public interface KnowledgeResourceEsRepository {
    void addKnowledgeResource(Long knowledgeId, ResourceEs resourceEs);

    void removeKnowledgeResource(Long knowledgeId, ResourceEs resourceEs);

    void addknowledgeResources(Long knowledgeId, List<ResourceEs> resourceEsList);

    void removeKnowledgeResources(Long knowledgeId, List<ResourceEs> resourceEsList);

    void updateResource(ResourceEs resourceEs);

    void deleteResource(ResourceEs resourceEs);
}
