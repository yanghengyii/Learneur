package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
            lqw.eq(Project::getName, project.getName());
            List list = getBaseMapper().selectList(lqw);
            if(list.size()== 0) {
                getBaseMapper().insert(project);
            }
        }

        return success;
    }
}
