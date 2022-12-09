package edu.whu.learneur.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.resource.entity.*;
import edu.whu.learneur.resource.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource")
public class ResourceController {

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

    /*
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

    @GetMapping("/projects")
    public ResponseEntity<IPage<Knowledge>> findKnowledge(@RequestParam(defaultValue = "0") Integer pageNum,
                                                          @RequestParam(defaultValue = "4") Integer pageSize) {
        return ResponseEntity.ok(knowledgeService.findKnowledge(pageNum, pageSize));
    }
*/

    @GetMapping("/{knowledgeId}/projects")
    public ResponseEntity<IPage<Project>> findProjects(@PathVariable Long knowledgeId,
                                                       @RequestParam(defaultValue = "0") Integer pageNum,
                                                       @RequestParam(defaultValue = "15") Integer pageSize){
        IPage<Project> res = projectService.findProjectPage(knowledgeId, pageNum, pageSize);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{knowledgeId}/lessons")
    public ResponseEntity<IPage<Lesson>> findLessons(@PathVariable Long knowledgeId,
                                                     @RequestParam(defaultValue = "0") Integer pageNum,
                                                     @RequestParam(defaultValue = "15") Integer pageSize) {
        IPage<Lesson> res = lessonService.findLessonPage(knowledgeId, pageNum, pageSize);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{knowledgeId}/books")
    public ResponseEntity<IPage<Book>> findBooks(@PathVariable Long knowledgeId,
                                                 @RequestParam(defaultValue = "0") Integer pageNum,
                                                 @RequestParam(defaultValue = "15") Integer pageSize) {
        IPage<Book> res = bookService.findBookPage(knowledgeId, pageNum, pageSize);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{knowledgeId}/tutorials")
    public ResponseEntity<IPage<Tutorial>> findTutorials(@PathVariable Long knowledgeId,
                                                        @RequestParam(defaultValue = "0") Integer pageNum,
                                                        @RequestParam(defaultValue = "15") Integer pageSize) {

        IPage<Tutorial> res = tutorialService.findTutorialPage(knowledgeId, pageNum, pageSize);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{knowledgeId}/videos")
    public ResponseEntity<IPage<Video>> findVideos(@PathVariable Long knowledgeId,
                                                   @RequestParam(defaultValue = "0") Integer pageNum,
                                                   @RequestParam(defaultValue = "15") Integer pageSize) {
        IPage<Video> res = videoService.findVideoPage(knowledgeId, pageNum, pageSize);
        return ResponseEntity.ok(res);
    }
}
