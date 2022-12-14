package edu.whu.learneur.neo4j.controller;

import edu.whu.learneur.neo4j.domain.Knowledge;
import edu.whu.learneur.neo4j.dto.KnowledgeAndRelations;
import edu.whu.learneur.neo4j.service.GKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *     知识图谱节点控制器
 *     用于处理前端的请求
 * </p>
 * @author Geraltigas
 * @since 2022-12-10
 * @version 1.0
 */
@Controller
@RequestMapping("/knowledge")
@CrossOrigin(origins = "*", maxAge = 3600)
public class KnowledgeController {

    @Autowired
    GKnowledgeService gKnowledgeService;


    /**
     * @param name 节点名称
     * @return 返回所有与该节点相关的节点和关系
     */
    @GetMapping("")
    public ResponseEntity<KnowledgeAndRelations> getGraphByName(@RequestParam String name) {
        KnowledgeAndRelations knowledgeAndRelations = gKnowledgeService.getGraphByName(name);
        if (knowledgeAndRelations.getKnowledges().size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(knowledgeAndRelations);
    }

    /**
     * @param id 节点id
     * @return 返回所有与该节点相关的节点和关系
     */
    @GetMapping("/{id}")
    public ResponseEntity<Knowledge> getKnowledgeById(@PathVariable Long id) {
        Knowledge knowledge = gKnowledgeService.getTagById(id);
        if (knowledge == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gKnowledgeService.getTagById(id));
    }

    /**
     * @param knowledge 节点
     * @return 返回添加的节点
     */
    @PostMapping("")
    public ResponseEntity<Knowledge> addKnowledge(@RequestBody Knowledge knowledge) {
        Knowledge knowledge1 = gKnowledgeService.addTag(knowledge);
        if (knowledge1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(knowledge1);
    }

    /**
     * @param knowledge 需要更新的知识的信息
     * @return 返回更新后的节点
     */
    @PutMapping("")
    public ResponseEntity<Knowledge> updateKnowledge(@RequestBody Knowledge knowledge) {
        Knowledge knowledge1 = gKnowledgeService.updateTagById(knowledge, knowledge.getId());
        if (knowledge1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(knowledge1);
    }

    /**
     * @param id 需要删除的节点id
     * @return 返回删除的节点
     */
    @DeleteMapping("")
    public ResponseEntity<Knowledge> deleteKnowledge(@RequestParam Long id) {
        Knowledge knowledge = gKnowledgeService.deleteTagById(id);
        if (knowledge == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(knowledge);
    }

    /**
     * @return 随机获取25个节点用于展示
     */
    @GetMapping("/first25")
    public ResponseEntity<List<Knowledge>> getFirst25Knowledge() {
        return ResponseEntity.ok(gKnowledgeService.getFirst25Knowledge());
    }

}
