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
    @Select("")
    IPage<Lesson> findLessonsByKnowledgeId(Long KnowledgeId, Page<?> page);
}
