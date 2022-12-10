package edu.whu.learneur.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.resource.entity.*;
import edu.whu.learneur.resource.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mysql-knowledge")
public class MysqlKnowledgeController {

    @Autowired
    private IKnowledgeService knowledgeService;

    @Autowired
    private IBookService bookService;
    @Autowired
    private IVideoService videoService;
    @Autowired
    private ILessonService lessonService;
    @Autowired
    private ITutorialService tutorialService;
    @Autowired
    private IProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<Knowledge> getKnowledge(@PathVariable Long id) {
        Knowledge res = knowledgeService.findById(id);
        if(res == null) {
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(res);
        }
    }

//    @GetMapping("")
//    public ResponseEntity<IPage<Knowledge>> findKnowledge(@RequestParam(defaultValue = "0") Integer pageNum,
//                                                          @RequestParam(defaultValue = "4") Integer pageSize) {
//        return ResponseEntity.ok(knowledgeService.findKnowledge(pageNum, pageSize));
//    }

}
