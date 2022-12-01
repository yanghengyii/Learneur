package edu.whu.learneur.service.impl;

import edu.whu.learneur.domain.Projects;
import edu.whu.learneur.dao.ProjectsDao;
import edu.whu.learneur.service.IProjectsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
public class ProjectsServiceImpl extends ServiceImpl<ProjectsDao, Projects> implements IProjectsService {

}
