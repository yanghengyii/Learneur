package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.resource.dao.LessonDao;
import edu.whu.learneur.resource.entity.Lesson;
import edu.whu.learneur.resource.service.ILessonService;
import edu.whu.learneur.exception.UserServiceException;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonServiceImpl extends ServiceImpl<LessonDao, Lesson> implements ILessonService {

    public List<Lesson> addLessons(List<Lesson> lessonList) throws UserServiceException {
        List<Lesson> success = new ArrayList<>();
        for(Lesson newLesson : lessonList){
            LambdaQueryWrapper<Lesson> lqw = new LambdaQueryWrapper<>();
            lqw.like(Lesson::getTitle, newLesson.getTitle());
            if(getBaseMapper().selectList(lqw).isEmpty()){
                getBaseMapper().insert(newLesson);
                success.add(newLesson);
            }
        }
        return success;
    }
    public IPage<Lesson> findLessonPage(Long knowledgeId, Integer pageNum, Integer pageSize) {
        return getBaseMapper().findLessonsByKnowledgeId(knowledgeId,new Page<>(pageNum, pageSize));
    }

    public Lesson findById(long id){
        return getBaseMapper().selectById(id);
    }
}
