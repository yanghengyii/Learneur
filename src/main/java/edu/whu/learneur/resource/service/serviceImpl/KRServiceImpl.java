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
            if(notExist(knowledgeId, book.getId(), ResourcesType.BOOK)) {
                KnowledgeResource knowledgeResource = new KnowledgeResource();
                knowledgeResource.setIdKnowledge(knowledgeId);
                knowledgeResource.setIdResource(book.getId());
                knowledgeResource.setType(ResourcesType.BOOK.getType());

                getBaseMapper().insert(knowledgeResource);
                results.add(knowledgeResource);
            }
        }
        return results;
    }

    public List<KnowledgeResource> addProject(Long knowledgeId,List<Project> projectList) throws ResourceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Project project:projectList) {
            if(notExist(knowledgeId, project.getIdProject(), ResourcesType.PROJECT)){
                KnowledgeResource knowledgeResources = new KnowledgeResource();
                knowledgeResources.setIdKnowledge(knowledgeId);
                knowledgeResources.setIdResource(project.getIdProject());
                knowledgeResources.setType(ResourcesType.PROJECT.getType());
                getBaseMapper().insert(knowledgeResources);
                results.add(knowledgeResources);
            }

        }
        return results;
    }

    public List<KnowledgeResource> addLesson(Long knowledgeId,List<Lesson> lessonList) throws ResourceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Lesson lesson:lessonList) {
            if(notExist(knowledgeId, lesson.getId(), ResourcesType.LESSON)){
                KnowledgeResource knowledgeResource = new KnowledgeResource();
                knowledgeResource.setIdKnowledge(knowledgeId);
                knowledgeResource.setIdResource(lesson.getId());
                knowledgeResource.setType(ResourcesType.LESSON.getType());
                getBaseMapper().insert(knowledgeResource);
                results.add(knowledgeResource);
            }
        }
        return results;
    }

    public List<KnowledgeResource> addTutorial(Long knowledgeId,List<Tutorial> tutorialList) throws ResourceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Tutorial tutorial:tutorialList) {
            if(notExist(knowledgeId, tutorial.getId(), ResourcesType.TUTORIAL)){
                KnowledgeResource knowledgeResource = new KnowledgeResource();
                knowledgeResource.setIdKnowledge(knowledgeId);
                knowledgeResource.setIdResource(tutorial.getId());
                knowledgeResource.setType(ResourcesType.TUTORIAL.getType());
                getBaseMapper().insert(knowledgeResource);
                results.add(knowledgeResource);
            }

        }
        return results;
    }

    public List<KnowledgeResource> addVideo(Long knowledgeId,List<Video> videoList) throws ResourceException{
        List<KnowledgeResource> results = new ArrayList<>();
        for(Video video:videoList) {
            if(notExist(knowledgeId, video.getId(), ResourcesType.VIDEO)){
                KnowledgeResource knowledgeResource = new KnowledgeResource();
                knowledgeResource.setIdKnowledge(knowledgeId);
                knowledgeResource.setIdResource(video.getId());
                knowledgeResource.setType(ResourcesType.VIDEO.getType());
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



    private boolean notExist(long knowledgeId, long resourceId, ResourcesType type) {
        LambdaQueryWrapper<KnowledgeResource> lqw = new LambdaQueryWrapper<>();
        lqw.eq(KnowledgeResource::getIdResource, resourceId)
                .eq(KnowledgeResource::getIdKnowledge, knowledgeId)
                .eq(KnowledgeResource::getType, type);
        return getBaseMapper().selectOne(lqw) == null;
    }








}
