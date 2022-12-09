package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.resource.dao.VideoDao;
import edu.whu.learneur.resource.entity.Video;
import edu.whu.learneur.resource.service.IVideoService;
import edu.whu.learneur.exception.UserServiceException;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoDao, Video> implements IVideoService {

    public List<Video> addVideos(List<Video> videoList) throws UserServiceException {
        List<Video> success = new ArrayList<>();
        for(Video video : videoList){
            LambdaQueryWrapper<Video> lqw = new LambdaQueryWrapper<>();
            lqw.like(Video::getBVid,video.getBVid());
            if(getBaseMapper().selectList(lqw).isEmpty()){
                getBaseMapper().insert(video);
                success.add(video);
            }
        }
        return success;
    }

    public IPage<Video> findVideoPage(Long knowledgeId, Integer pageNum, Integer pageSize) {
        return null;
    }

    public Video findById(long id){
        return getBaseMapper().selectById(id);
    }

}
