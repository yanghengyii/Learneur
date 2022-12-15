package edu.whu.learneur.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.resource.entity.Book;
import edu.whu.learneur.resource.entity.Lesson;
import edu.whu.learneur.resource.entity.Project;
import edu.whu.learneur.resource.service.ILessonService;
import edu.whu.learneur.resource.service.ITutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutorials")
public class TutorialController {
    @Autowired
    private ITutorialService tutorialService;

    @Autowired
    private ILessonService lessonService;

    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<IPage<Lesson>> findAllLesson(@RequestParam(defaultValue = "0") Integer pageNum,
                                                         @RequestParam(defaultValue = "20") Integer pageSize) {

        IPage<Lesson> res = lessonService.findAllLessons(pageNum, pageSize);
        return ResponseEntity.ok(res);
    }


}
