package edu.whu.learneur.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.resource.entity.Video;
import edu.whu.learneur.resource.service.ILessonService;
import edu.whu.learneur.resource.service.IVideoService;
import edu.whu.learneur.resource.service.serviceImpl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private IVideoService videoService;


    @GetMapping("")
    @CrossOrigin
    public ResponseEntity<IPage<Video>> findAllVideos(@RequestParam(defaultValue = "0") Integer pageNum,
                                                      @RequestParam(defaultValue = "20") Integer pageSize){
        IPage<Video> res = videoService.findAllVideos(pageNum, pageSize);
        return ResponseEntity.ok(res);

    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Video> findAllVideos(@PathVariable Long id){
        Video res = videoService.findById(id);
        return ResponseEntity.ok(res);

    }

}
