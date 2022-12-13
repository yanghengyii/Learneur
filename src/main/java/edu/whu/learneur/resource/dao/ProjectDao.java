package edu.whu.learneur.resource.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.resource.entity.Project;
import org.apache.ibatis.annotations.*;

import javax.sound.sampled.Port;

@Mapper
public interface ProjectDao extends BaseMapper<Project> {
    @Select("SELECT project.* FROM knowledge_resource join project on knowledge_resource.id_resource = project.id_project " +
            "where id_knowledge = #{knowledgeId} and type = 3")
    IPage<Project> findProjectsByKnowledgeId(Long knowledgeId, Page<Project> page);

    @Select("SELECT * FROM project")
    @Results({@Result(id = true, property = "idProject", column = "id_project"),
            @Result(property = "knowledge", column = "id_project",
            many = @Many(
                    select = "edu.whu.learneur.resource.dao.KnowledgeDao.findKnowledgeByProjectId"
            ))
    })
    IPage<Project> findProjects(Page<Project> page);

    @Insert("INSERT INTO knowledge_resource VALUES(#{projectId},#{knowledgeId}, 3)")
    void insertKR(long projectId,long knowledgeId);

    @Select("select exists (select * from knowledge_resource where id_knowledge = #{knowledgeId} "
            + "and id_resource = #{projectId} and type = 3)")
    int existKR(long knowledgeId, long projectId);
}
