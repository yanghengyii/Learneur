package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.constant.ResourcesType;
import edu.whu.learneur.exception.ResourceException;
import edu.whu.learneur.resource.dao.KRDao;
import edu.whu.learneur.resource.entity.*;
import edu.whu.learneur.resource.service.IKRService;
import edu.whu.learneur.exception.UserServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KRServiceImpl extends ServiceImpl<KRDao, KnowledgeResource> implements IKRService {

    public List<KnowledgeResource> addBook(Long knowledgeId,List<Book> bookList) throws ResourceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Book book:bookList) {
            System.out.println("----------------------1");
            System.out.println(book.getIdBook());
            System.out.println(knowledgeId);
            if(notExist(knowledgeId, book.getIdBook(), 2)) {
                System.out.println("----------------------2");
                KnowledgeResource knowledgeResource = new KnowledgeResource();
                knowledgeResource.setIdKnowledge(knowledgeId);
                knowledgeResource.setIdResource(book.getIdBook());
                knowledgeResource.setType(2);

                getBaseMapper().insert(knowledgeResource);
                results.add(knowledgeResource);
            }
        }
        return results;
    }

    public List<KnowledgeResource> addProject(Long knowledgeId,List<Project> projectList) throws ResourceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Project project:projectList) {
            if(notExist(knowledgeId, project.getIdProject(), 3)){
                KnowledgeResource knowledgeResources = new KnowledgeResource();
                knowledgeResources.setIdKnowledge(knowledgeId);
                knowledgeResources.setIdResource(project.getIdProject());
                knowledgeResources.setType(3);
                getBaseMapper().insert(knowledgeResources);
                results.add(knowledgeResources);
            }

        }
        return results;
    }

    public List<KnowledgeResource> addLesson(Long knowledgeId,List<Lesson> lessonList) throws ResourceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Lesson lesson:lessonList) {
            if(notExist(knowledgeId, lesson.getIdLesson(), 1)){
                KnowledgeResource knowledgeResource = new KnowledgeResource();
                knowledgeResource.setIdKnowledge(knowledgeId);
                knowledgeResource.setIdResource(lesson.getIdLesson());
                knowledgeResource.setType(1);
                getBaseMapper().insert(knowledgeResource);
                results.add(knowledgeResource);
            }
        }
        return results;
    }

    public List<KnowledgeResource> addTutorial(Long knowledgeId,List<Tutorial> tutorialList) throws ResourceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Tutorial tutorial:tutorialList) {
            if(notExist(knowledgeId, tutorial.getIdTutorial(),4)){
                KnowledgeResource knowledgeResource = new KnowledgeResource();
                knowledgeResource.setIdKnowledge(knowledgeId);
                knowledgeResource.setIdResource(tutorial.getIdTutorial());
                knowledgeResource.setType(4);
                getBaseMapper().insert(knowledgeResource);
                results.add(knowledgeResource);
            }

        }
        return results;
    }

    public List<KnowledgeResource> addVideo(Long knowledgeId,List<Video> videoList) throws ResourceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Video video:videoList) {
            if(notExist(knowledgeId, video.getIdVideo(), 5)){
                KnowledgeResource knowledgeResource = new KnowledgeResource();
                knowledgeResource.setIdKnowledge(knowledgeId);
                knowledgeResource.setIdResource(video.getIdVideo());
                knowledgeResource.setType(5);
                getBaseMapper().insert(knowledgeResource);
                results.add(knowledgeResource);
            }
        }
        return results;
    }
    public IPage<KnowledgeResource> findResourcePageByKnowledgeId(int current,int size,Long knowledgeId) throws ResourceException{
        IPage<KnowledgeResource> result = new Page<>(current,size);
        IPage<KnowledgeResource> returnPage;
        LambdaQueryWrapper<KnowledgeResource> lqw = new LambdaQueryWrapper<>();
        lqw.like(KnowledgeResource::getIdKnowledge,knowledgeId);
        try{
            returnPage = getBaseMapper().selectPage(result,lqw);
        }
        catch (Exception e){
            throw new ResourceException("查询失败");
        }
        return returnPage;
    }



    private boolean notExist(long knowledgeId, long resourceId, int type) {
        boolean result=false;
        LambdaQueryWrapper<KnowledgeResource> lqw = new LambdaQueryWrapper<>();
        System.out.println("---------------3");
        lqw.eq(KnowledgeResource::getIdResource, resourceId)
                .eq(KnowledgeResource::getIdKnowledge, knowledgeId)
                .eq(KnowledgeResource::getType, type);

        try{
            if(getBaseMapper().selectOne(lqw)==null)
                result = true;
        }catch (Exception e) {
            throw new ResourceException("不存在");
        }
        return result;
    }







}
