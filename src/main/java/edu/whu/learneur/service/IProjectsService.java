package edu.whu.learneur.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Projects;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 项目 服务类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
public interface IProjectsService extends IService<Projects> {
    /**
     * 根据项目id查找相关信息
     * @param idProject    电子数id
     * @return
     */
    Projects findProject(Long idProject);

    /**
     * 获取项目资源
     * @param pages 页数
     * @param cols  行数
     * @return
     */
    IPage<Projects> findProjects(int pages, int cols);

    /**
     * 获取一批项目资源
     * @param idProjects    项目id
     * @param pages         页数
     * @param cols          列数
     * @return
     */
    IPage<Projects> findProjectsByBatch(List<Long> idProjects, int pages, int cols);

    /**
     * 保存一本项目资源
     * @param project    项目
     * @return
     */
    boolean insertProject(Projects project);

    /**
     * 删除一本项目资源
     * @param idProject    项目id
     * @return
     */
    boolean deleteProject(Long idProject);

    /**
     * 删除一组项目
     * @param idProjects   一批项目id
     * @return
     */
    boolean deleteProjects(List<Long> idProjects);

    /**
     * 修改部分Project资源信息
     * @param idProject    项目id
     * @param project      修改后的项目数据
     * @return
     */
    boolean updateProject(Long idProject, Projects project);
}
