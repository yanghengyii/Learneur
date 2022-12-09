package edu.whu.learneur.resource.controller;

import edu.whu.learneur.resource.service.ILessonService;
import edu.whu.learneur.resource.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private IVideoService videoService;

    @Autowired
    private ILessonService lessonService;


}
