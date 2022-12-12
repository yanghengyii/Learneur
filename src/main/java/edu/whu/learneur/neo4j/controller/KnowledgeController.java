package edu.whu.learneur.neo4j.controller;

import edu.whu.learneur.neo4j.domain.Knowledge;
import edu.whu.learneur.neo4j.domain.Relation;
import edu.whu.learneur.neo4j.dto.KnowledgeAndRelations;
import edu.whu.learneur.neo4j.service.GKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/knowledge")
@CrossOrigin(origins = "*", maxAge = 3600)
public class KnowledgeController {

    @Autowired
    GKnowledgeService gKnowledgeService;

    @GetMapping("")
    public ResponseEntity<KnowledgeAndRelations> getGraphByName(@RequestParam String name) {
        return ResponseEntity.ok(gKnowledgeService.getGraphByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Knowledge> getKnowledgeById(@PathVariable Long id) {
        return ResponseEntity.ok(gKnowledgeService.getTagById(id));
    }

    @PostMapping("")
    public ResponseEntity<Knowledge> addKnowledge(@RequestBody Knowledge knowledge) {
        return ResponseEntity.ok(gKnowledgeService.addTag(knowledge));
    }

    @PutMapping("")
    public ResponseEntity<Knowledge> updateKnowledge(@RequestBody Knowledge knowledge) {
        return ResponseEntity.ok(gKnowledgeService.updateTagById(knowledge, knowledge.getId()));
    }
    @DeleteMapping("")
    public ResponseEntity<Knowledge> deleteKnowledge(@RequestParam Long id) {
        return ResponseEntity.ok(gKnowledgeService.deleteTagById(id));
    }

    @GetMapping("/first25")
    public ResponseEntity<List<Knowledge>> getFirst25Knowledge() {
        return ResponseEntity.ok(gKnowledgeService.getFirst25Knowledge());
    }

}
