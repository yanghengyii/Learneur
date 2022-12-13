package edu.whu.learneur.resource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.resource.entity.KnowledgeResource;
import edu.whu.learneur.resource.entity.Project;
import edu.whu.learneur.resource.entity.Tutorial;
import edu.whu.learneur.resource.entity.Video;
import org.apache.ibatis.annotations.*;

@Mapper
public interface VideoDao extends BaseMapper<Video> {
    @Select("SELECT video.* FROM knowledge_resource join video on knowledge_resource.id_resource = video.id_video where id_knowledge = #{KnowledgeId} and type = 5")
    IPage<Video> findVideosByKnowledgeId(Long KnowledgeId, Page<Tutorial> page);

    @Select("SELECT * FROM video")
    @Results({@Result(id = true, property = "id", column = "id_video"),
            @Result(property = "knowledge", column = "id_video",
                    many = @Many(
                            select = "edu.whu.learneur.resource.dao.KnowledgeDao.findKnowledgeByVideoId"
                    ))
    })
    IPage<Video> findVideos(Page<Video> page);

    @Insert("INSERT INTO knowledge_resource VALUES(#{videoId},#{knowledgeId}, 5)")
    void insertKR(long videoId,long knowledgeId);

    @Select("select exists (select * from knowledge_resource where id_knowledge = #{knowledgeId} "
    + "and id_resource = #{videoId} and type = 5)")
    int existKR(long knowledgeId, long videoId);
}
