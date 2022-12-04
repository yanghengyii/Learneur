package edu.whu.learneur.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.PageList;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.aspect.ResourcesLogger;
import edu.whu.learneur.constant.ResourcesType;
import edu.whu.learneur.crawler.entity.Project;
import edu.whu.learneur.crawler.github.ProjectCrawler;
import edu.whu.learneur.domain.Projects;
import edu.whu.learneur.dao.ProjectsDao;
import edu.whu.learneur.exception.ResourcesServiceException;
import edu.whu.learneur.service.IProjectsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 项目 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
@ResourcesLogger(type = ResourcesType.PROJECT)
public class ProjectsServiceImpl extends ServiceImpl<ProjectsDao, Projects> implements IProjectsService {
    @Autowired
    public ProjectsDao projectsDao;

    @Autowired
    private ProjectCrawler projectCrawler;


    public static final String DEFAULT_COVER = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fstatic.open-open.com%2Fnews%2FuploadImg%2F20130417%2F20130417214726_527.jpg&refer=http%3A%2F%2Fstatic.open-open.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672645549&t=29dbf354979c7c1f04607a8ef76fe9a6";

    @Override
    public Projects findProject(Long idProject) {
        return projectsDao.selectById(idProject);
    }

    @Override
    public IPage<Projects> findProjects(int pages, int cols) {
        return projectsDao.selectPage(new Page<>(pages, cols), new LambdaQueryWrapper<>());
    }

    @Override
    public IPage<Projects> findProjectsByBatch(List<Long> idProjects, int pages, int cols) {
        LambdaQueryWrapper<Projects> lqw = new LambdaQueryWrapper<>();
        lqw.in(Projects::getIdProject, idProjects);
        return projectsDao.selectPage(new Page<>(pages, cols), lqw);
    }

    @Override
    @Transactional
    public boolean insertProject(Projects project) {
        return projectsDao.insert(project) > 0;
    }

    @Override
    @Transactional
    public boolean deleteProject(Long idProject) {
        return projectsDao.deleteById(idProject) > 0;
    }

    @Override
    @Transactional
    public boolean deleteProjects(List<Long> idProjects) {
        int res = projectsDao.deleteBatchIds(idProjects);
        if(res != idProjects.size()) {
            throw new ResourcesServiceException("项目资源删除失败");
        }
        return true;
    }

    @Override
    public boolean updateProject(Long idProject, Projects project) {
        Projects oldProject = projectsDao.selectById(idProject);
        if(Objects.isNull(oldProject)) {
            throw new ResourcesServiceException("试图修改未知项目资源");
        }
        BeanUtils.copyProperties(project, oldProject);
        return projectsDao.updateById(oldProject) > 0;
    }
}
