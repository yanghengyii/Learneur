package edu.whu.learneur.neo4j.service.impl;

import edu.whu.learneur.neo4j.dao.KnowledgeRepoInterface;
import edu.whu.learneur.neo4j.dao.KnowledgeRepository;
import edu.whu.learneur.neo4j.domain.Knowledge;
import edu.whu.learneur.neo4j.domain.Relation;
import edu.whu.learneur.neo4j.dto.KnowledgeAndRelations;
import edu.whu.learneur.neo4j.service.GKnowledgeService;
import edu.whu.learneur.resource.service.IKnowledgeService;
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

    private static final Integer DEPTH = 3;
    @Autowired
    KnowledgeRepository knowledgeRepository;
    @Autowired
    KnowledgeRepoInterface knowledgeRepoInterface;

    @Autowired
    private IKnowledgeService knowledgeService;

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
        edu.whu.learneur.resource.entity.Knowledge knowledge1 = knowledgeService.findByName(knowledge.getName());

        if (knowledge1 != null)
        {
            knowledge.setForeignId(knowledge1.getId());
            return knowledgeRepository.save(knowledge);
        }
        else
        {
            edu.whu.learneur.resource.entity.Knowledge knowledge2 = new edu.whu.learneur.resource.entity.Knowledge();
            knowledge2.setKnowledgeName(knowledge.getName());
            knowledge2.setKnowledgeDescription(knowledge.getDescription());
            knowledgeService.addKnowledge(knowledge2);
            knowledge.setForeignId(knowledgeService.findByName(knowledge.getName()).getId());
            return knowledgeRepository.save(knowledge);
        }
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
    public Relation addRelationByNames(String name1, String name2, String type, String description) {
        return knowledgeRepoInterface.addRelationByNames(name1,name2,type,description).orElse(null);
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
    public Relation deleteRelationById(Long id,Long end) {
        return knowledgeRepoInterface.deleteRelationById(id,end).orElse(null);
    }

    @Override
    public KnowledgeAndRelations getGraphByName(String name) {
        KnowledgeAndRelations knowledgeAndRelations = new KnowledgeAndRelations();
        knowledgeAndRelations.setKnowledges(knowledgeRepoInterface.findAllRelated(name).orElse(null));
        List<Knowledge> knowledges = knowledgeRepository.findByName(name).orElse(null);
        if (knowledges != null && knowledges.size() > 0) {
            knowledgeAndRelations.getKnowledges().add(knowledges.get(0));
        }
        List<Relation> relations = knowledgeRepoInterface.findRelationByName(name,3).orElse(null);
        knowledgeAndRelations.setRelations(relations);
        return knowledgeAndRelations;
    }
}
