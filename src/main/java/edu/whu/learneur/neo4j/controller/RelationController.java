package edu.whu.learneur.neo4j.controller;

import edu.whu.learneur.neo4j.domain.Relation;
import edu.whu.learneur.neo4j.service.GKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/relation")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RelationController {
    @Autowired
    GKnowledgeService gKnowledgeService;

    @PostMapping("")
    public ResponseEntity<Relation> addRelation(@RequestBody Long knowledgeId, @RequestBody Long relatedId, @RequestBody String type, @RequestBody String description) {
        return ResponseEntity.ok(gKnowledgeService.addRelation(knowledgeId, relatedId, type, description));
    }

    @PutMapping("")
    public ResponseEntity<List<Relation>> updateRelation(@RequestParam Long relationId, @RequestParam String type, @RequestParam String description) {
        return ResponseEntity.ok(gKnowledgeService.updateRelationById(relationId, type, description));
    }

    @DeleteMapping("")
    public ResponseEntity<Relation> deleteRelation(@RequestParam Long relationId) {
        return ResponseEntity.ok(gKnowledgeService.deleteRelationById(relationId));
    }
}
