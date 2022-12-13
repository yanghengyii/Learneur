package edu.whu.learneur.resource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.learneur.resource.entity.Knowledge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
@Mapper
public interface KnowledgeDao extends BaseMapper<Knowledge> {
    @Select("select * from knowledge")
    List<Knowledge> findAll();

    @Select("SELECT knowledge.* from knowledge join knowledge_resource on knowledge.id_knowledge= knowledge_resource.id_knowledge" +
            " where knowledge_resource.id_resource = #{projectId} and type = 3")
    List<Knowledge> findKnowledgeByProjectId(long projectId);
    @Select("SELECT knowledge.* from knowledge join knowledge_resource on knowledge.id_knowledge= knowledge_resource.id_knowledge" +
            " where knowledge_resource.id_resource = #{videoId} and type = 5")
    List<Knowledge> findKnowledgeByVideoId(long videoId);
    @Select("SELECT knowledge.* from knowledge join knowledge_resource on knowledge.id_knowledge= knowledge_resource.id_knowledge" +
            " where knowledge_resource.id_resource = #{bookId} and type = 2")
    List<Knowledge> findKnowledgeByBookId(long bookId);

    @Select("SELECT knowledge.* from knowledge join knowledge_resource on knowledge.id_knowledge= knowledge_resource.id_knowledge" +
            " where knowledge_resource.id_resource = #{lessonId} and type = 1")
    List<Knowledge> findKnowledgeByLessonId(long lessonId);
}
