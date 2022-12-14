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


/**
 * <p>
 *     知识图谱数据库服务实现类
 *     继承GKnowledgeService接口
 *     用于实现服务
 * </p>
 * @author Geraltigas
 * @since 2022-12-10
 * @version 1.0
 */
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


    /**
     * <p>
     *     根据节点name查找节点
     * </p>
     * @param id 节点id
     * @return 返回节点
     */
    public Knowledge getTagById(Long id)
    {
        return knowledgeRepository.findById(id).orElse(null);
    }


    /**
     * <p>
     *     根据节点name查找节点
     * </p>
     * @param name 节点name
     * @return 返回节点列表
     */
    public List<Knowledge> getTagByName(String name)
    {
        return knowledgeRepository.findByName(name).orElse(null);
    }


    /**
     * <p>
     *     向两种数据库中添加节点
     * </p>
     * @param knowledge 节点
     * @return 返回添加的节点
     */
    public Knowledge addTag(Knowledge knowledge)
    {

        edu.whu.learneur.resource.entity.Knowledge knowledge1 = knowledgeService.findByName(knowledge.getName());
        if (knowledge1 != null)
        {
//            knowledge.setForeignId(knowledge1.getId());
            return null;
        }
        else
        {
            edu.whu.learneur.resource.entity.Knowledge knowledge2 = new edu.whu.learneur.resource.entity.Knowledge();
            knowledge2.setKnowledgeName(knowledge.getName());
            knowledge2.setKnowledgeDescription(knowledge.getDescription());
            knowledgeService.addKnowledge(knowledge2);
            knowledge.setForeignId(knowledgeService.findByName(knowledge.getName()).getId());
        }
        return knowledgeRepository.save(knowledge);
    }


    /**
     * <p>
     *     随机获取25个节点
     * </p>
     * @return 节点列表
     */
    public List<Knowledge> getFirst25Knowledge()
    {
        return knowledgeRepository.findFirst25Knowledge().orElse(null);
    }


    /**
     * <p>
     *     根据节点id更新节点
     * </p>
     * @param relationId 关系id
     * @param type 关系类型
     * @param description 关系描述
     * @return 返回更新的节点
     */
    public List<Relation> updateRelationById(Long relationId, String type, String description) {
        return knowledgeRepoInterface.updateRelationById(relationId,type,description).orElse(null);
    }


/**
     * <p>
     *     向节点间添加关系
     * </p>
     * @param knowledgeId 节点id
     * @param relatedId 相关节点id
     * @param type 关系类型
     * @param description 关系描述
     * @return 返回添加的关系
     */
    @Override
    public Relation addRelation(Long knowledgeId, Long relatedId, String type, String description) {
        return knowledgeRepoInterface.addRelation(knowledgeId,relatedId,type,description).orElse(null);
    }


    /**
     * @param name1 节点1name
     * @param name2 节点2name
     * @param type 关系类型
     * @return 返回添加的关系
     */
    @Override
    public Relation addRelationByNames(String name1, String name2, String type) {
        String description = name1 + "-[" + type + "]->" + name2;
        return knowledgeRepoInterface.addRelationByNames(name1.toLowerCase(),name2.toLowerCase(),type,description).orElse(null);
    }


    /**
     * @param knowledge 节点
     * @param id 节点id
     * @return 返回更新的节点
     */
    @Override
    public Knowledge updateTagById(Knowledge knowledge, Long id) {
        Knowledge knowledge1 = knowledgeRepository.findById(id).orElse(null);
        if (knowledge1 == null)
        {
            return null;
        }
        else
        {
            return knowledgeRepoInterface.updateTagById(knowledge,id).orElse(null);
        }
    }

    /**
     * @param id 节点id
     * @return 返回删除的节点
     */
    @Override
    public Knowledge deleteTagById(Long id) {
        return knowledgeRepoInterface.deleteById(id).orElse(null);
    }


    /**
     * @param id 起始节点id
     * @param end 目标节点id
     * @return 返回删除的关系
     */
    @Override
    public Relation deleteRelationById(Long id,Long end) {
        return knowledgeRepoInterface.deleteRelationById(id,end).orElse(null);
    }


    /**
     * @param name 节点name
     * @return 返回相关的节点和关系
     */
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
