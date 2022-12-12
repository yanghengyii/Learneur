package edu.whu.learneur.resource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.resource.entity.Lesson;
import edu.whu.learneur.resource.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LessonDao extends BaseMapper<Lesson> {
    @Select("SELECT id_lesson,title,description,link,img_path FROM knowledge_resource natural join lesson where id_knowledge = ${KnowledgeId}")
    IPage<Lesson> findLessonsByKnowledgeId(Long KnowledgeId, Page<Lesson> page);
}
