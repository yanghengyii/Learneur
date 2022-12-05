package edu.whu.learneur.neo4j.service.impl;

import edu.whu.learneur.neo4j.dao.KnowledgeRepository;
import edu.whu.learneur.neo4j.domain.Knowledge;
import edu.whu.learneur.neo4j.dto.KnowledgeAndRelations;
import edu.whu.learneur.neo4j.service.GKnowledgeService;
import io.netty.util.internal.shaded.org.jctools.queues.MpscArrayQueue;
import org.apache.http.annotation.Obsolete;
import org.apache.ibatis.annotations.Update;
import org.neo4j.driver.internal.InternalRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.redis.connection.ReactiveSetCommands;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GKnowledgeServiceImpl implements GKnowledgeService {

    @Autowired
    KnowledgeRepository knowledgeRepository;
    @Autowired
    Neo4jClient neo4jClient;

    public Knowledge getTagById(Long id)
    {
        return knowledgeRepository.findById(id).orElse(null);
    }

    public List<Knowledge> getTagByName(String name)
    {
        return knowledgeRepository.findByName(name).orElse(null);
    }

    public Knowledge addTag(Knowledge knowledge)
    {
        return knowledgeRepository.save(knowledge);
    }

    public Map<String,Object>[] updateRelationById(Long relationId, String type, String description) {
        Map[] update = neo4jClient
                .query(String.format("MATCH (n:knowledge)-[r]->(m:knowledge) WHERE id(r) = %d MERGE (n)-[r2:%s {type:'%s',description:'%s'}]->(m) delete r RETURN r2",relationId,type,type,description))
                .fetch().all().toArray(new Map[0]);
        return update;
    }

    @Override
    public Map<String,Object>[] addRelation(Long knowledgeId, Long relatedId, String type, String description) {
        Map[] add = neo4jClient
                .query(String.format("MATCH (n:knowledge),(m:knowledge) WHERE id(n) = %d AND id(m)=%d MERGE (n)-[r:%s {type:'%s',description:'%s'}]->(m) RETURN r", knowledgeId,relatedId,type,type,description))
                .fetch().all().toArray(new Map[0]);
        Map map = add[0];
        InternalRelationship internalRelationship = (InternalRelationship) map.get("r");
        Long startNodeId = internalRelationship.startNodeId();
        Long endNodeId = internalRelationship.endNodeId();
        String types = internalRelationship.type();
        String descriptions = internalRelationship.get("description").asString();

        Map<String,Object> res = new HashMap<>();
        res.put("startNodeId",startNodeId);
        res.put("endNodeId",endNodeId);
        res.put("type",types);
        res.put("description",descriptions);

        return new Map[]{res};
    }

    @Override
    public Map<String,Object>[] updateTagById(Knowledge knowledge, Long id) {
        Map[] update = neo4jClient
                .query(String.format("MATCH (n:knowledge) WHERE id(n) = %d SET n.name='%s',n.description='%s' RETURN n", id,knowledge.getName(),knowledge.getDescription()))
                .fetch().all().toArray(new Map[0]);
        return update;
    }

    @Override
    public Map<String,Object>[] deleteTagById(Long id) {
        Map[] delete = neo4jClient
                .query(String.format("MATCH (n:knowledge) WHERE id(n) = %d DETACH DELETE n", id))
                .fetch().all().toArray(new Map[0]);
        return delete;
    }


    @Override
    public Map<String,Object>[] deleteRelationById(Long id) {
        Map[] delete = neo4jClient
                .query(String.format("MATCH (n:knowledge)-[r]->(m:knowledge) WHERE id(r) = %d DELETE r RETURN r", id))
                .fetch().all().toArray(new Map[0]);
        return delete;
    }


    @Override
    public KnowledgeAndRelations getGraphByName(String name) {
        KnowledgeAndRelations knowledgeAndRelations = new KnowledgeAndRelations();
        knowledgeAndRelations.setKnowledges(knowledgeRepository.findAllRelated(name).orElse(null));
        knowledgeAndRelations.getKnowledges().add(knowledgeRepository.findByName(name).orElse(null).get(0));
        Map[] pres = neo4jClient
                .query(String.format("MATCH (n:knowledge)-[r:preKnowledge]->(m:knowledge) WHERE n.name = '%s' RETURN r.type AS type,id(n) AS source ,id(m) AS target, r.description AS description", name))
                        .fetch().all().toArray(new Map[0]);
        knowledgeAndRelations.setPreKnowledges(pres);
        Map[] includes = neo4jClient
                .query(String.format("MATCH (n:knowledge)-[r:include]->(m:knowledge) WHERE n.name = '%s' RETURN r.type AS type,id(n) AS source ,id(m) AS target, r.description AS description", name))
                .fetch().all().toArray(new Map[0]);
        knowledgeAndRelations.setIncludes(includes);
        Map[] commons = neo4jClient
                .query(String.format("MATCH (n:knowledge)-[r:common]->(m:knowledge) WHERE n.name = '%s' RETURN r.type AS type,id(n) AS source ,id(m) AS target, r.description AS description", name))
                .fetch().all().toArray(new Map[0]);
        knowledgeAndRelations.setCommons(commons);
        Map[] associateds = neo4jClient
                .query(String.format("MATCH (n:knowledge)-[r:associated]->(m:knowledge) WHERE n.name = '%s' RETURN r.type AS type,id(n) AS source ,id(m) AS target, r.description AS description", name))
                .fetch().all().toArray(new Map[0]);
        knowledgeAndRelations.setAssociateds(associateds);
        return knowledgeAndRelations;
    }
}
