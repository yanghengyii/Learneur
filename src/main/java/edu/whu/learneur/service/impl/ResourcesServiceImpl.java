package edu.whu.learneur.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.domain.Books;
import edu.whu.learneur.domain.Lessons;
import edu.whu.learneur.domain.Projects;
import edu.whu.learneur.domain.Resources;
import edu.whu.learneur.dao.ResourcesDao;
import edu.whu.learneur.exception.ResourcesServiceException;
import edu.whu.learneur.service.IBooksService;
import edu.whu.learneur.service.ILessonsService;
import edu.whu.learneur.service.IProjectsService;
import edu.whu.learneur.service.IResourcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesDao, Resources> implements IResourcesService {
    @Autowired
    private IBooksService booksService;

    @Autowired
    private ILessonsService lessonsService;

    @Autowired
    private IProjectsService projectsService;


    @Override
    public <T> IPage<T> findResources(Class<T> requiredType, int pages, int cols) {
        if(requiredType == Books.class) {
            IPage<Books> books = booksService.findBooks(pages, cols);
            IPage<T> ret = new Page<>(pages, cols);
            BeanUtils.copyProperties(books, ret);
            return ret;
        } else if (requiredType == Lessons.class) {
            IPage<Lessons> lessons = lessonsService.findLessons(pages, cols);
            IPage<T> ret = new Page<>(pages, cols);
            BeanUtils.copyProperties(lessons, ret);
            return ret;
        } else if (requiredType == ProjectsServiceImpl.class) {
            IPage<Projects> lessons = projectsService.findProjects(pages, cols);
            IPage<T> ret = new Page<>(pages, cols);
            BeanUtils.copyProperties(lessons, ret);
            return ret;
        }
        else {
            throw new ResourcesServiceException("错误资源类型");
        }
    }
}
