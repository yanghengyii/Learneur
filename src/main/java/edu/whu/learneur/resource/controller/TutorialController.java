package edu.whu.learneur.resource.controller;

import edu.whu.learneur.resource.service.ILessonService;
import edu.whu.learneur.resource.service.ITutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutorials")
public class TutorialController {
    @Autowired
    private ITutorialService tutorialService;

    @Autowired
    private ILessonService lessonService;
}
