package edu.whu.learneur.resource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.resource.entity.Tutorial;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TutorialDao extends BaseMapper<Tutorial> {
    @Select("SELECT tutorial.* FROM knowledge_resource join tutorial on knowledge.id_resource = tutorial.id_tutorial where id_knowledge = #{KnowledgeId} and type = 4" )
    IPage<Tutorial> findTutorialsByKnowledgeId(Long KnowledgeId, Page<Tutorial> page);

    @Insert("INSERT INTO knowledge_resource VALUES(#{tutorialId},#{knowledgeId}, 4)")
    void insertKR(long tutorialId,long knowledgeId);

    @Select("select exists (select * from knowledge_resource where id_knowledge = #{knowledgeId} "
            + "and id_resource = #{tutorialId} and type = 4)")
    int existKR(long knowledgeId, long tutorialId);
}
