package edu.whu.learneur.neo4j.controller;

import edu.whu.learneur.neo4j.domain.Relation;
import edu.whu.learneur.neo4j.service.GKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *     知识图谱关系控制器
 *     用于处理前端的请求
 * </p>
 * @author Geraltigas
 * @since 2022-12-10
 * @version 1.0
 */
@Controller
@RequestMapping("/relation")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RelationController {
    @Autowired
    GKnowledgeService gKnowledgeService;

    /**
     * @param relation 关系
     * @return 返回添加的关系
     */
    @PostMapping("")
    public ResponseEntity<Relation> addRelation(@RequestBody Relation relation) {
        Relation relation1 = gKnowledgeService.addRelation(relation.getStart(), relation.getEnd(), relation.getType(), relation.getDescription());
        if (relation1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(relation1);
    }


    /**
     * @param relationId 关系id
     * @param type 关系类型
     * @param description 关系描述
     * @return 返回修改后的关系
     */
    @PutMapping("")
    public ResponseEntity<List<Relation>> updateRelation(@RequestParam Long relationId, @RequestParam String type, @RequestParam String description) {
        List<Relation> relations = gKnowledgeService.updateRelationById(relationId, type, description);
        if (relations.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(relations);
    }

    /**
     * @param start 起始节点
     * @param end 终止节点
     * @return 返回删除的关系
     */
    @DeleteMapping("")
    public ResponseEntity<Relation> deleteRelation(@RequestParam Long start, @RequestParam Long end) {
        Relation relation = gKnowledgeService.deleteRelationById(start, end);
        if (relation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(relation);
    }
}
