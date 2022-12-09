package edu.whu.learneur.neo4j.service.impl;

import edu.whu.learneur.neo4j.dao.KnowledgeRepoInterface;
import edu.whu.learneur.neo4j.dao.KnowledgeRepository;
import edu.whu.learneur.neo4j.domain.Knowledge;
import edu.whu.learneur.neo4j.domain.Relation;
import edu.whu.learneur.neo4j.dto.KnowledgeAndRelations;
import edu.whu.learneur.neo4j.service.GKnowledgeService;
import org.neo4j.driver.Record;
import org.neo4j.driver.internal.InternalRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GKnowledgeServiceImpl implements GKnowledgeService {

    @Autowired
    KnowledgeRepository knowledgeRepository;
    @Autowired
    KnowledgeRepoInterface knowledgeRepoInterface;

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

    public List<Knowledge> getFirst25Knowledge()
    {
        return knowledgeRepository.findFirst25Knowledge().orElse(null);
    }
//
//    public Map<String,Object>[] updateRelationById(Long relationId, String type, String description) {
//        Map[] update = neo4jClient
//                .query(String.format("MATCH (n:knowledge)-[r]->(m:knowledge) WHERE id(r) = %d MERGE (n)-[r2:%s {type:'%s',description:'%s'}]->(m) delete r RETURN r2",relationId,type,type,description))
//                .fetch().all().toArray(new Map[0]);
//        return update;
//    }

    public List<Relation> updateRelationById(Long relationId, String type, String description) {
        return knowledgeRepoInterface.updateRelationById(relationId,type,description).orElse(null);
    }

    @Override
    public Relation addRelation(Long knowledgeId, Long relatedId, String type, String description) {
        return knowledgeRepoInterface.addRelation(knowledgeId,relatedId,type,description).orElse(null);
    }

    @Override
    public Knowledge updateTagById(Knowledge knowledge, Long id) {
        return knowledgeRepoInterface.updateTagById(knowledge,id).orElse(null);
    }

    @Override
    public Knowledge deleteTagById(Long id) {
        return knowledgeRepoInterface.deleteById(id).orElse(null);
    }

    @Override
    public Relation deleteRelationById(Long id) {
        return knowledgeRepoInterface.deleteRelationById(id).orElse(null);
    }

    @Override
    public KnowledgeAndRelations getGraphByName(String name) {
        KnowledgeAndRelations knowledgeAndRelations = new KnowledgeAndRelations();
        knowledgeAndRelations.setKnowledges(knowledgeRepository.findAllRelated(name).orElse(null));
        knowledgeAndRelations.getKnowledges().add(knowledgeRepository.findByName(name).orElse(null).get(0));
        List<Relation> pres = knowledgeRepoInterface.findRelationByNameAndType(name,"preKnowledge").orElse(null);
        List<Relation> includes = knowledgeRepoInterface.findRelationByNameAndType(name,"include").orElse(null);
        List<Relation> commons = knowledgeRepoInterface.findRelationByNameAndType(name,"common").orElse(null);
        List<Relation> associateds = knowledgeRepoInterface.findRelationByNameAndType(name,"associated").orElse(null);
        knowledgeAndRelations.setPreKnowledges(pres);
        knowledgeAndRelations.setIncludes(includes);
        knowledgeAndRelations.setCommons(commons);
        knowledgeAndRelations.setAssociateds(associateds);
        return knowledgeAndRelations;
    }
}
