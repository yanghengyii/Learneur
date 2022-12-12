package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.resource.dao.ProjectDao;
import edu.whu.learneur.resource.entity.Project;
import edu.whu.learneur.resource.service.IProjectService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, Project> implements IProjectService {
    public List<Project> addProjects(List<Project> projects) {
        List<Project> success = new ArrayList<>();
        for(Project project: projects) {
            LambdaQueryWrapper<Project> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Project::getLink, project.getLink());
            Project tmp =  getBaseMapper().selectOne(lqw);
            if(tmp == null) {
                getBaseMapper().insert(project);
                success.add(project);
            }
            else if(!tmp.equals(project)){
                project.setIdProject(tmp.getIdProject());
                getBaseMapper().updateById(project);
            }
        }
        return success;
    }

    @Override
    public IPage<Project> findProjectPage(Long knowledgeId, Integer pageNum, Integer pageSize) {
        Page<Project> page = new Page<>(pageNum, pageSize);
        return getBaseMapper().findProjectsByKnowledgeId(knowledgeId, page);
    }

    @Override
    public IPage<Project> findAllProjects(Integer pageNum, Integer pageSize) {
        Page<Project> page = new Page<>(pageNum, pageSize);
        return getBaseMapper().findProjects(page);
    }

    @Override
    public Project findById(Long id) {
        return getBaseMapper().selectById(id);
    }


}
