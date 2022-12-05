package edu.whu.learneur.controller;


import edu.whu.learneur.domain.Knowledges;
import edu.whu.learneur.service.IKnowledgesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 知识点 前端控制器
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@RestController
@RequestMapping("/knowledges")
public class KnowledgesController {
    @Autowired
    private IKnowledgesService knowledgesService;

    @GetMapping("/knowledge-node/{idKnowledge}")
    public ResponseEntity<Knowledges> getKnowledgeNode(
            @PathVariable Long idKnowledge,
            @RequestParam(value = "pages", defaultValue = "0") int pages,
            @RequestParam(value = "cols", defaultValue = "15") int cols
    ) {
        return ResponseEntity.ok(knowledgesService.findKnowledgeNode(idKnowledge, pages, cols));
    }

    @GetMapping("/get-knowledge-by-resource/{idKnowledge}")
    public ResponseEntity<List<Knowledges>> getKnowledgeByResource(
            @PathVariable Long idKnowledge
    ) {
        return ResponseEntity.ok(knowledgesService.findKnowledgeByResource(idKnowledge));
    }
}

