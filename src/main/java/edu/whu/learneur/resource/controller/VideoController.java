package edu.whu.learneur.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.resource.entity.Video;
import edu.whu.learneur.resource.service.ILessonService;
import edu.whu.learneur.resource.service.IVideoService;
import edu.whu.learneur.resource.service.serviceImpl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private IVideoService videoService;


    @GetMapping("")
    public ResponseEntity<IPage<Video>> findAllVideos(@RequestParam Integer pageNum,
                                                      @RequestParam Integer pageSize){
        IPage<Video> res = videoService.findAllVideos(pageNum, pageSize);
        return ResponseEntity.ok(res);

    }

}
