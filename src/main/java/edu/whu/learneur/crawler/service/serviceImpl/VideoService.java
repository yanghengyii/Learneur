package edu.whu.learneur.crawler.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.crawler.dao.VideoDao;
import edu.whu.learneur.crawler.entity.Book;
import edu.whu.learneur.crawler.entity.Video;
import edu.whu.learneur.crawler.service.IVideoService;
import edu.whu.learneur.exception.UserServiceException;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService extends ServiceImpl<VideoDao, Video> implements IVideoService {

    @SelectKey(statement = "select last_insert_id()",keyProperty = "id",keyColumn = "id_video",resultType = Long.class,before = true)
    public List<Video> addBooks(List<Video> videoList) throws UserServiceException {
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
