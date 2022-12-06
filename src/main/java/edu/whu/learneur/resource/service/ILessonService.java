package edu.whu.learneur.resource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.resource.entity.Lesson;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ILessonService extends IService<Lesson> {
    List<Lesson> addBooks(List<Lesson> lessonList) throws UserServiceException;
}
