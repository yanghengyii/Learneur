package edu.whu.learneur.resource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.resource.entity.*;

import java.util.List;


public interface IKRService extends IService<KnowledgeResource> {

    List<KnowledgeResource> addBook(Long knowledgeId, List<Book> bookList) throws UserServiceException;

    List<KnowledgeResource> addLesson(Long knowledgeId,List<Lesson> lessonList) throws UserServiceException;

    List<KnowledgeResource> addTutorial(Long knowledgeId,List<Tutorial> tutorialList) throws UserServiceException;

    List<KnowledgeResource> addVideo(Long knowledgeId,List<Video> videoList) throws UserServiceException;

    List<KnowledgeResource> addProject(Long knowledgeId,List<Project> projectList) throws UserServiceException;


}
