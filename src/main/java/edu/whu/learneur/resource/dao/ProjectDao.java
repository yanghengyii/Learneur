package edu.whu.learneur.resource.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.resource.entity.Project;
import org.apache.ibatis.annotations.*;

import javax.sound.sampled.Port;

@Mapper
public interface ProjectDao extends BaseMapper<Project> {
    @Select("SELECT id_project,name,update_time,link,description,star_gazers,forks,home_page,readme FROM knowledge_resource natural join project where id_knowledge = ${KnowledgeId}")
    IPage<Project> findProjectsByKnowledgeId(Long knowledgeId, Page<Project> page);

    @Select("SELECT * FROM project")
    @Results({@Result(id = true, property = "idProject", column = "id_project"),
            @Result(property = "knowledge", column = "id_resource",
            many = @Many(
                    select = "edu.whu.learneur.resource.dao.KnowledgeDao.findKnowledgeByProjectId"
            ))
    })
    IPage<Project> findProjects(Page<Project> page);
}
