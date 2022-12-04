package edu.whu.learneur.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.constant.ResourcesType;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
@Transactional
public class ResourcesServiceImpl extends ServiceImpl<ResourcesDao, Resources> implements IResourcesService {
    @Autowired
    private ResourcesDao resourcesDao;
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
            for (Books record : books.getRecords()) {
                LambdaQueryWrapper<Resources> lqw = new LambdaQueryWrapper<>();
                lqw.eq(Resources::getIdSpecific, record.getIdBook()).eq(Resources::getType, ResourcesType.BOOK.getType());
                record.setIdResource(resourcesDao.selectOne(lqw).getIdResources());
            }
            IPage<T> ret = new Page<>(pages, cols);
            BeanUtils.copyProperties(books, ret);
            return ret;
        } else if (requiredType == Lessons.class) {
            IPage<Lessons> lessons = lessonsService.findLessons(pages, cols);
            for (Lessons record : lessons.getRecords()) {
                LambdaQueryWrapper<Resources> lqw = new LambdaQueryWrapper<>();
                lqw.eq(Resources::getIdSpecific, record.getIdLesson()).eq(Resources::getType, ResourcesType.BOOK.getType());
                record.setIdResource(resourcesDao.selectOne(lqw).getIdResources());
            }
            IPage<T> ret = new Page<>(pages, cols);
            BeanUtils.copyProperties(lessons, ret);
            return ret;
        } else if (requiredType == ProjectsServiceImpl.class) {
            IPage<Projects> projects = projectsService.findProjects(pages, cols);
            for (Projects record : projects.getRecords()) {
                LambdaQueryWrapper<Resources> lqw = new LambdaQueryWrapper<>();
                lqw.eq(Resources::getIdSpecific, record.getIdProject()).eq(Resources::getType, ResourcesType.BOOK.getType());
                record.setIdResource(resourcesDao.selectOne(lqw).getIdResources());
            }
            IPage<T> ret = new Page<>(pages, cols);
            BeanUtils.copyProperties(projects, ret);
            return ret;
        }
        else {
            throw new ResourcesServiceException("错误资源类型");
        }
    }

    @Override
    public <T> T findResourceByIdSpecify(Long idSpecify, Class<T> requiredType) {
        if(requiredType == Books.class) {
            /* 图书资源 */
            Books book = booksService.findBook(idSpecify);
            LambdaQueryWrapper<Resources> lqw = new LambdaQueryWrapper<>();
            return (T) book;
        } else if (requiredType == Lessons.class) {
            /* 网壳资源 */
            Lessons lesson = lessonsService.findLesson(idSpecify);
            return (T) lesson;
        } else if (requiredType == Projects.class) {
            /* 项目资源 */
            Projects project = projectsService.findProject(idSpecify);
            return (T) project;
        } else {
            throw new ResourcesServiceException("错误资源类型");
        }
    }

    @Override
    public boolean saveOrUpdateResource(Object resource) {
        Resources r = new Resources();
        if(resource instanceof Books) {
            r.setType(ResourcesType.BOOK.getType());

            Books book = (Books) resource;
            booksService.insertBook(book);

            r.setIdSpecific(book.getIdBook());
            return resourcesDao.insert(r) > 0;
        } else if (resource instanceof Lessons) {
            r.setType(ResourcesType.LESSON.getType());

            Lessons lesson = (Lessons) resource;
            lesson = lessonsService.saveOrUpdateLesson(lesson);

            r.setIdSpecific(lesson.getIdLesson());
            return resourcesDao.insert(r) > 0;
        } else if (resource instanceof Projects) {
            r.setType(ResourcesType.PROJECT.getType());

            Projects project = (Projects) resource;
            project = projectsService.saveOrUpdateProject(project);

            r.setIdSpecific(project.getIdProject());
            return resourcesDao.insert(r) > 0;
        } else {
            throw new ResourcesServiceException("试图保存位置资源");
        }
    }

    @Override
    public boolean saveOrUpdateResources(List<Object> resources) {
        boolean ret = true;
        for (Object resource : resources) {
            ret &= saveOrUpdateResource(resources);
        }
        if(!ret) {
            throw new ResourcesServiceException("批保存失败!");
        }
        return true;
    }

    @Override
    public boolean deleteResource(Long idSpecific, int type) {
        LambdaQueryWrapper<Resources> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Resources::getIdSpecific, idSpecific).eq(Resources::getType, type);
        boolean ret = resourcesDao.delete(lqw) > 1;
        if(type == ResourcesType.BOOK.getType()) {
            return (ret & booksService.deleteBook(idSpecific));
        } else if (type == ResourcesType.LESSON.getType()) {
            return (ret & lessonsService.deleteLesson(idSpecific));
        } else if (type == ResourcesType.PROJECT.getType()) {
            return (ret & projectsService.deleteProject(idSpecific));
        } else {
            throw new ResourcesServiceException("删除位置种类资源");
        }
    }

    @Override
    public boolean deleteResources(List<Long> idSpecifics, int type) {
        LambdaQueryWrapper<Resources> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Resources::getType, type).in(Resources::getIdSpecific, idSpecifics);
        int ret = resourcesDao.delete(lqw);
        if(ret != idSpecifics.size()) {
            throw new ResourcesServiceException("资源删除失败");
        }
        if(type == ResourcesType.BOOK.getType()) {
            return booksService.deleteBooks(idSpecifics);
        } else if (type == ResourcesType.LESSON.getType()) {
            return lessonsService.deleteLessons(idSpecifics);
        } else if (type == ResourcesType.PROJECT.getType()) {
            return projectsService.deleteProjects(idSpecifics);
        } else {
            throw new ResourcesServiceException("试图删除未知类型资源");
        }
    }

    @Override
    public boolean deleteResourcesByIds(List<Long> idResources) {
        boolean ret = true;
        for (Long id : idResources) {
            Resources resource = resourcesDao.selectById(id);
            ret &= resourcesDao.deleteById(id) > 0;
            ret &= deleteResource(resource.getIdSpecific(), resource.getType());
        }
        if(!ret) {
            throw new ResourcesServiceException("删除操作失败");
        }
        return true;
    }

}
