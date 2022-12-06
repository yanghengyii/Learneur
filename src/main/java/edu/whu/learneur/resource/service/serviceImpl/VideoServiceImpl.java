package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
            if(getBaseMapper().selectList(lqw).size()==0){
                getBaseMapper().insert(video);
                success.add(video);
            }
        }
        return success;
    }

    public Video findById(long id){
        return getBaseMapper().selectById(id);
    }

}
