package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.resource.dao.VideoDao;
import edu.whu.learneur.resource.entity.Video;
import edu.whu.learneur.resource.service.IVideoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoDao, Video> implements IVideoService {
    @Override
    public List<Video> addVideos(List<Video> videoList,long knowledgeId){
        List<Video> success = new ArrayList<>();
        for(Video video : videoList){
            LambdaQueryWrapper<Video> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Video::getBvid,video.getBvid());
            Video tmp = getBaseMapper().selectOne(lqw);
            if(tmp == null){
                getBaseMapper().insert(video);

                success.add(video);
            }
            else if(!tmp.equals(video)) {
                video.setIdVideo(tmp.getIdVideo());
                getBaseMapper().updateById(video);
            }

            if(getBaseMapper().existKR(knowledgeId, video.getIdVideo()) == 0){
                getBaseMapper().insertKR(video.getIdVideo(),knowledgeId);
            }
        }
        return success;
    }
    @Override
    public IPage<Video> findVideoPage(Long knowledgeId, Integer pageNum, Integer pageSize) {
        return getBaseMapper().findVideosByKnowledgeId(knowledgeId, new Page<>(pageNum, pageSize));

    }
    @Override
    public IPage<Video> findAllVideos(Integer pageNum, Integer pageSize) {
        return getBaseMapper().findVideos(new Page<>(pageNum, pageSize));
    }
    public Video findById(long id){
        return getBaseMapper().findVideoById(id);
    }

}
