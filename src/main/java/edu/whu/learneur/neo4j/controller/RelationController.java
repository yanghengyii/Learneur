package edu.whu.learneur.neo4j.controller;

import edu.whu.learneur.neo4j.service.GKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/relation")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RelationController {
    @Autowired
    GKnowledgeService gKnowledgeService;

    @PostMapping("")
    public ResponseEntity<Map<String,Object>[]> addRelation(@RequestBody Map<String,Object> map) {
        return ResponseEntity.ok(gKnowledgeService.addRelation(Long.valueOf(((Integer) map.get("knowledgeId")).longValue()), Long.valueOf(((Integer) map.get("relatedId")).longValue()), (String) map.get("type"), (String) map.get("description")));
    }

    @PutMapping("")
    public ResponseEntity<Map<String,Object>[]> updateRelation(@RequestParam Long relationId, @RequestParam String type, @RequestParam String description) {
        return ResponseEntity.ok(gKnowledgeService.updateRelationById(relationId, type, description));
    }


    @DeleteMapping("")
    public ResponseEntity<Map<String,Object>[]> deleteRelation(@RequestParam Long relationId) {
        return ResponseEntity.ok(gKnowledgeService.deleteRelationById(relationId));
    }
}
