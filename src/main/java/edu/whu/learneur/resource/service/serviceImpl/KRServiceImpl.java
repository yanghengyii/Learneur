package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.resource.dao.KRDao;
import edu.whu.learneur.resource.entity.*;
import edu.whu.learneur.resource.service.IKRService;
import edu.whu.learneur.exception.UserServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KRServiceImpl extends ServiceImpl<KRDao, KnowledgeResource> implements IKRService {

    public List<KnowledgeResource> addBook(Long knowledgeId,List<Book> bookList) throws UserServiceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Book book:bookList) {
            KnowledgeResource knowledgeResources = new KnowledgeResource();
            knowledgeResources.setIdKnowledge(knowledgeId);
            knowledgeResources.setIdResources(book.getId());
            knowledgeResources.setType(2);
            getBaseMapper().insert(knowledgeResources);
            results.add(knowledgeResources);
        }
        return results;
    }

    public List<KnowledgeResource> addProject(Long knowledgeId,List<Project> projectList) throws UserServiceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Project project:projectList) {
            KnowledgeResource knowledgeResources = new KnowledgeResource();
            knowledgeResources.setIdKnowledge(knowledgeId);
            knowledgeResources.setIdResources(project.getIdProject());
            knowledgeResources.setType(3);
            getBaseMapper().insert(knowledgeResources);
            results.add(knowledgeResources);
        }
        return results;
    }

    public List<KnowledgeResource> addLesson(Long knowledgeId,List<Lesson> lessonList) throws UserServiceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Lesson lesson:lessonList) {
            KnowledgeResource knowledgesResources = new KnowledgeResource();
            knowledgesResources.setIdKnowledge(knowledgeId);
            knowledgesResources.setIdResources(lesson.getId());
            knowledgesResources.setType(1);
            getBaseMapper().insert(knowledgesResources);
            results.add(knowledgesResources);
        }
        return results;
    }

    public List<KnowledgeResource> addTutorial(Long knowledgeId,List<Tutorial> tutorialList) throws UserServiceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Tutorial tutorial:tutorialList) {
            KnowledgeResource knowledgesResources = new KnowledgeResource();
            knowledgesResources.setIdKnowledge(knowledgeId);
            knowledgesResources.setIdResources(tutorial.getId());
            knowledgesResources.setType(4);
            getBaseMapper().insert(knowledgesResources);
            results.add(knowledgesResources);
        }
        return results;
    }

    public List<KnowledgeResource> addVideo(Long knowledgeId,List<Video> videoList) throws UserServiceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Video video:videoList) {
            KnowledgeResource knowledgesResources = new KnowledgeResource();
            knowledgesResources.setIdKnowledge(knowledgeId);
            knowledgesResources.setIdResources(video.getId());
            knowledgesResources.setType(5);
            getBaseMapper().insert(knowledgesResources);
            results.add(knowledgesResources);
        }
        return results;
    }

    public List<Long> findKnowledgeIdByResource(Long resourceId){
        LambdaQueryWrapper<KnowledgeResource> lqw = new LambdaQueryWrapper<>();
        lqw.like(KnowledgeResource::getIdResources,resourceId);
        List<Long> result=null;
        try{
            for(KnowledgeResource KR:getBaseMapper().selectList(lqw)){
                result.add(KR.getIdKnowledge());
            }
        }
        catch (Exception e){
            throw new RuntimeException("未找到对应知识点");
        }
        return result;
    }

    public IPage<KnowledgeResource> findResourcePageByKnowledgeId(int current,int size,Long KnowledgeId){
        IPage<KnowledgeResource> result = new Page<>(current,size);
        IPage<KnowledgeResource> returnPage = null;
        LambdaQueryWrapper<KnowledgeResource> lqw = new LambdaQueryWrapper<>();
        lqw.like(KnowledgeResource::getIdKnowledge,KnowledgeId);
        try{
            returnPage = getBaseMapper().selectPage(result,lqw);
        }
        catch (Exception e){
            throw new UserServiceException("查询失败");
        }
        return returnPage;
    }












}
