package edu.whu.learneur.resource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.resource.entity.Video;

import java.util.List;

public interface IVideoService extends IService<Video> {
    List<Video> addVideos(List<Video> videoList) throws UserServiceException;
}
