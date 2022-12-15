package edu.whu.learneur.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.resource.entity.Lesson;
import edu.whu.learneur.resource.entity.Video;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ILessonService extends IService<Lesson> {
    List<Lesson> addLessons(List<Lesson> lessonList, long knowledgeId) throws UserServiceException;

    IPage<Lesson> findLessonPage(Long knowledgeId, Integer pageNum, Integer pageSize);

    IPage<Lesson> findAllLessons(Integer pageNum, Integer pageSize);
}
