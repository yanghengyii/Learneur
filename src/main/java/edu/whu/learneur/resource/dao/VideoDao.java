package edu.whu.learneur.resource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.resource.entity.Project;
import edu.whu.learneur.resource.entity.Tutorial;
import edu.whu.learneur.resource.entity.Video;
import org.apache.ibatis.annotations.*;

@Mapper
public interface VideoDao extends BaseMapper<Video> {
    @Select("SELECT id_video,author,BVid,length,pic_path,description,title FROM knowledge_resource natural join video where id_knowledge = ${KnowledgeId}")
    IPage<Video> findVideosByKnowledgeId(Long KnowledgeId, Page<Tutorial> page);

    @Select("SELECT * FROM video")
    @Results({@Result(id = true, property = "id", column = "id_video"),
            @Result(property = "knowledge", column = "id_resource",
                    many = @Many(
                            select = "edu.whu.learneur.resource.dao.KnowledgeDao.findKnowledgeByVideoId"
                    ))
    })
    IPage<Video> findVideos(Page<Video> page);
}
