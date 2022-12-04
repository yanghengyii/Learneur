package edu.whu.learneur.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.aspect.ResourcesLogger;
import edu.whu.learneur.constant.ResourcesType;
import edu.whu.learneur.domain.Lessons;
import edu.whu.learneur.dao.LessonsDao;
import edu.whu.learneur.exception.ResourcesServiceException;
import edu.whu.learneur.service.ILessonsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 网课 服务实现类
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Service
@ResourcesLogger(type = ResourcesType.LESSON)
public class LessonsServiceImpl extends ServiceImpl<LessonsDao, Lessons> implements ILessonsService {

    @Autowired
    private LessonsDao lessonsDao;

    private static final String DEFAULT_COVER = "https://img2.baidu.com/it/u=965757385,3358206628&fm=253&fmt=auto&app=138&f=PNG?w=500&h=250";


    @Override
    public Lessons findLesson(Long idLesson) {
        return lessonsDao.selectById(idLesson);
    }

    @Override
    public IPage<Lessons> findLessons(int pages, int cols) {
        return lessonsDao.selectPage(new Page<>(pages, cols), new LambdaQueryWrapper<>());
    }

    @Override
    public IPage<Lessons> findLessonsByBatch(List<Long> idLessons, int pages, int cols) {
        LambdaQueryWrapper<Lessons> lqw = new LambdaQueryWrapper<>();
        lqw.in(Lessons::getIdLesson, idLessons);
        return lessonsDao.selectPage(new Page<>(pages, cols), lqw);
    }

    @Override
    @Transactional
    public Lessons insertLesson(Lessons lesson) {
        if("".equals(lesson.getImgPath().trim())) {
            lesson.setImgPath(DEFAULT_COVER);
        }
        lessonsDao.updateById(lesson);
        return lesson;
    }

    @Override
    public Lessons saveOrUpdateLesson(Lessons lesson) {
        LambdaQueryWrapper<Lessons> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Lessons::getTitle, lesson.getTitle());
        Lessons oldLesson = lessonsDao.selectOne(lqw);
        if(Objects.isNull(oldLesson)) {
            lessonsDao.insert(lesson);
            return lesson;
        }
        else {
            BeanUtils.copyProperties(lesson, oldLesson);
            lessonsDao.updateById(oldLesson);
            return oldLesson;
        }
    }

    @Override
    @Transactional
    public boolean deleteLesson(Long idLesson) {
        return lessonsDao.deleteById(idLesson) > 0;
    }

    @Override
    @Transactional
    public boolean deleteLessons(List<Long> idLessons) {
        int res = lessonsDao.deleteBatchIds(idLessons);
        if(res != idLessons.size()) {
            throw new ResourcesServiceException("网课资源删除失败");
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updateLesson(Long idLesson, Lessons lesson) {
        Lessons oldLesson = lessonsDao.selectById(idLesson);
        if(Objects.isNull(oldLesson)) {
            throw new ResourcesServiceException("视图修改未知网课资源");
        }
        BeanUtils.copyProperties(lesson, oldLesson);
        return lessonsDao.updateById(oldLesson) > 0;
    }
}
