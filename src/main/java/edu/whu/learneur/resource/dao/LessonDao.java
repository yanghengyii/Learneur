package edu.whu.learneur.resource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.resource.entity.Lesson;
import edu.whu.learneur.resource.entity.Project;
import edu.whu.learneur.resource.entity.Video;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LessonDao extends BaseMapper<Lesson> {
    @Select("SELECT lesson.* FROM knowledge_resource join lesson on knowledge_resource.id_resource = lesson.id_lesson where id_knowledge = #{KnowledgeId} and type = 1")
    IPage<Lesson> findLessonsByKnowledgeId(Long KnowledgeId, Page<Lesson> page);
    @Insert("INSERT INTO knowledge_resource VALUES(#{lessonId},#{knowledgeId}, 1)")
    void insertKR(long lessonId,long knowledgeId);
    @Select("SELECT * FROM lesson")
    @Results({@Result(id = true, property = "id", column = "id_lesson"),
            @Result(property = "knowledge", column = "id_lesson",
                    many = @Many(
                            select = "edu.whu.learneur.resource.dao.KnowledgeDao.findKnowledgeByLessonId"
                    ))
    })
    IPage<Lesson> findLessons(Page<Lesson> page);

    @Select("select exists (select * from knowledge_resource where id_knowledge = #{knowledgeId} "
            + "and id_resource = #{lessonId} and type = 1)")
    int existKR(long knowledgeId, long lessonId);
}
