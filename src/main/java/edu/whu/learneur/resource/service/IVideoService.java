package edu.whu.learneur.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.resource.entity.Video;

import java.util.List;

public interface IVideoService extends IService<Video> {
    List<Video> addVideos(List<Video> videoList,long knowledgeId) throws UserServiceException;

    IPage<Video> findVideoPage(Long knowledgeId, Integer pageNum, Integer pageSize);

    IPage<Video> findAllVideos(Integer pageNum, Integer pageSize);

    Video findById(long id);


}
