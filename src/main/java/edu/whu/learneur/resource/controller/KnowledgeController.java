package edu.whu.learneur.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.domain.Knowledge;
import edu.whu.learneur.domain.Project;
import edu.whu.learneur.resource.entity.Book;
import edu.whu.learneur.resource.entity.Lesson;
import edu.whu.learneur.resource.entity.Video;
import edu.whu.learneur.resource.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

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

    @GetMapping("")
    public ResponseEntity<Knowledge> getKnowledge(Long id) {
        Knowledge res = knowledgeService.findById(id);
        if(res == null) {
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping("")
    public ResponseEntity<IPage<Knowledge>> findKnowledge(@RequestParam(defaultValue = "0") Integer pageNum,
                                                          @RequestParam(defaultValue = "4") Integer pageSize) {
        return ResponseEntity.ok(knowledgeService.findKnowledge(pageNum, pageSize));
    }

    @GetMapping("")
    public ResponseEntity<IPage<Project>> findProjects(Long id_knowledge,
                                                       @RequestParam(defaultValue = "0") Integer pageNum,
                                                       @RequestParam(defaultValue = "15") Integer pageSize){
        return
    }
}
