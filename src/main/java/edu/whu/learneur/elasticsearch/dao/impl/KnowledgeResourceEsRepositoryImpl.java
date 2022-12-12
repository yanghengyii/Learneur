package edu.whu.learneur.elasticsearch.dao.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.ChildScoreMode;
import co.elastic.clients.elasticsearch._types.query_dsl.NestedQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.json.JsonpMappingException;
import edu.whu.learneur.elasticsearch.dao.KnowledgeResourceEsRepository;
import edu.whu.learneur.elasticsearch.entity.ResourceEs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.UncategorizedElasticsearchException;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.RefreshPolicy;
import org.springframework.data.elasticsearch.core.ScriptType;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

public class KnowledgeResourceEsRepositoryImpl implements KnowledgeResourceEsRepository {

    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    private static final IndexCoordinates IndexKnowledge = IndexCoordinates.of("learneur_knowledge");

    @Value("${elasticsearch.script.update.add-knowledge-resources}")
    private String scriptAddKnowledgeResources;

    @Value("${elasticsearch.script.update.add-knowledge-resource}")
    private String scriptAddKnowledgeResource;

    @Value("${elasticsearch.script.update.remove-knowledge-resources}")
    private String scriptRemoveKnowledgeResources;

    @Value("${elasticsearch.script.update.remove-knowledge-resource}")
    private String scriptRemoveKnowledgeResource;

    @Override
    public void addKnowledgeResource(Long knowledgeId, ResourceEs resourceEs) {
        UpdateQuery updateQuery = buildUpdateQuery(knowledgeId, scriptAddKnowledgeResource, resourceEs);
        try {
            elasticsearchOperations.update(updateQuery, IndexKnowledge);
        } catch (UncategorizedElasticsearchException e) {
            System.out.println(e.getResponseBody());
        } catch (JsonpMappingException e) {
            System.out.println(e.path() + " "+ e.getMessage());
            throw e;
        }
    }
    @Override
    public void removeKnowledgeResource(Long knowledgeId, ResourceEs resourceEs) {
        UpdateQuery updateQuery = buildUpdateQuery(knowledgeId, scriptRemoveKnowledgeResource, resourceEs);
        try {
            elasticsearchOperations.update(updateQuery, IndexKnowledge);
        } catch (UncategorizedElasticsearchException e) {
            System.out.println(e.getResponseBody());
        }
    }

    @Override
    public void addknowledgeResources(Long knowledgeId, List<ResourceEs> resourceEsList) {
        UpdateQuery updateQuery = buildUpdateQuery(knowledgeId, scriptAddKnowledgeResources, resourceEsList);
        try {
            elasticsearchOperations.update(updateQuery, IndexKnowledge);
        } catch (UncategorizedElasticsearchException e) {
            System.out.println(e.getResponseBody());
        }
    }

    @Override
    public void removeKnowledgeResources(Long knowledgeId, List<ResourceEs> resourceEsList) {
        UpdateQuery updateQuery =  buildUpdateQuery(knowledgeId, scriptRemoveKnowledgeResources, resourceEsList);
        try {
            elasticsearchOperations.update(updateQuery, IndexKnowledge);
        } catch (UncategorizedElasticsearchException e) {
            System.out.println(e.getResponseBody());
        }
    }
@Resource
    private ElasticsearchClient elasticsearchClient;
    @Override
    public void updateResource(ResourceEs resourceEs) {
        System.out.println(resourceEs.getName());
        Query query = buildSpecificResourceQuery(resourceEs);
        UpdateQuery updateQuery = buildUpdateQuery(query, scriptAddKnowledgeResource, resourceEs);
        try {
            elasticsearchOperations.updateByQuery(updateQuery, IndexKnowledge);
        } catch (UncategorizedElasticsearchException e) {
            System.out.println(e.getResponseBody());
        }
    }

    @Override
    public void deleteResource(ResourceEs resourceEs) {
        Query query = buildSpecificResourceQuery(resourceEs);
        UpdateQuery updateQuery = buildUpdateQuery(query, scriptRemoveKnowledgeResource, resourceEs);
        try {
            elasticsearchOperations.updateByQuery(updateQuery, IndexKnowledge);
        } catch (UncategorizedElasticsearchException e) {
            System.out.println(e.getResponseBody());
        }
    }

    private UpdateQuery buildUpdateQuery(Long id, String script, ResourceEs resourceEs) {
        return UpdateQuery.builder(id.toString())
                .withScriptType(ScriptType.INLINE)
                .withScript(script)
                .withParams(Collections.singletonMap("resource", resourceEs))
                .withRetryOnConflict(5)
                .build();
    }

    private UpdateQuery buildUpdateQuery(Query query, String script, ResourceEs resourceEs) {
        return UpdateQuery.builder(query)
                .withScriptType(ScriptType.INLINE)
                .withScript(script)
                .withParams(Collections.singletonMap("resource", resourceEs))
                .withRetryOnConflict(5)
                .build();
    }

    private UpdateQuery buildUpdateQuery(Long id, String script, List<ResourceEs> resourceEsList) {
        return UpdateQuery.builder(id.toString())
                .withScriptType(ScriptType.INLINE)
                .withScript(script)
                .withParams(Collections.singletonMap("resources", resourceEsList))
                .withRetryOnConflict(5)
                .build();
    }

    private Query buildSpecificResourceQuery(ResourceEs resourceEs) {
        return NativeQuery.builder()
                .withQuery(new NestedQuery.Builder()
                        .path("resources")
                        .query(new BoolQuery.Builder()
                                .must(new TermQuery.Builder()
                                        .field("resources.resourceId")
                                        .value(resourceEs.getResourceId().toString())
                                        .build()._toQuery())
                                .must(new TermQuery.Builder()
                                        .field("resources.resourceType")
                                        .value(resourceEs.getResourceType().toString())
                                        .build()._toQuery())
                                .build()._toQuery())
                        .scoreMode(ChildScoreMode.Avg)
                        .build()._toQuery())
                .build();
    }
}

