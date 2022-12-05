package edu.whu.learneur.crawler.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.crawler.dao.KRDao;
import edu.whu.learneur.crawler.entity.Book;
import edu.whu.learneur.crawler.entity.Lesson;
import edu.whu.learneur.crawler.entity.Tutorial;
import edu.whu.learneur.crawler.entity.Video;
import edu.whu.learneur.crawler.service.IKRService;
import edu.whu.learneur.domain.KnowledgesResources;
import edu.whu.learneur.domain.Resources;
import edu.whu.learneur.exception.UserServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KRServiceImpl extends ServiceImpl<KRDao, KnowledgesResources> implements IKRService {

    public List<KnowledgesResources> addBook(Long knowledgeId,List<Book> bookList) throws UserServiceException{
        List<KnowledgesResources> results = new ArrayList<>();
        for(Book book:bookList) {
            KnowledgesResources knowledgesResources = new KnowledgesResources();
            knowledgesResources.setIdKnowledge(knowledgeId);
            knowledgesResources.setIdResources(book.getId());
            knowledgesResources.setType(2);
            getBaseMapper().insert(knowledgesResources);
            results.add(knowledgesResources);
        }
        return results;
    }

    public List<KnowledgesResources> addLesson(Long knowledgeId,List<Lesson> lessonList) throws UserServiceException{
        List<KnowledgesResources> results = new ArrayList<>();
        for(Lesson lesson:lessonList) {
            KnowledgesResources knowledgesResources = new KnowledgesResources();
            knowledgesResources.setIdKnowledge(knowledgeId);
            knowledgesResources.setIdResources(lesson.getId());
            knowledgesResources.setType(1);
            getBaseMapper().insert(knowledgesResources);
            results.add(knowledgesResources);
        }
        return results;
    }

    public List<KnowledgesResources> addTutorial(Long knowledgeId,List<Tutorial> tutorialList) throws UserServiceException{
        List<KnowledgesResources> results = new ArrayList<>();
        for(Tutorial tutorial:tutorialList) {
            KnowledgesResources knowledgesResources = new KnowledgesResources();
            knowledgesResources.setIdKnowledge(knowledgeId);
            knowledgesResources.setIdResources(tutorial.getId());
            knowledgesResources.setType(4);
            getBaseMapper().insert(knowledgesResources);
            results.add(knowledgesResources);
        }
        return results;
    }

    public List<KnowledgesResources> addVideo(Long knowledgeId,List<Video> videoList) throws UserServiceException{
        List<KnowledgesResources> results = new ArrayList<>();
        for(Video video:videoList) {
            KnowledgesResources knowledgesResources = new KnowledgesResources();
            knowledgesResources.setIdKnowledge(knowledgeId);
            knowledgesResources.setIdResources(video.getId());
            knowledgesResources.setType(5);
            getBaseMapper().insert(knowledgesResources);
            results.add(knowledgesResources);
        }
        return results;
    }

    public List<Long> findKnowledgeIdByResource(Long ResourceId){
        LambdaQueryWrapper<KnowledgesResources> lqw = new LambdaQueryWrapper<>();
        lqw.like(KnowledgesResources::getIdResources,ResourceId);
        List<Long> result=null;
        try{
            for(KnowledgesResources KR:getBaseMapper().selectList(lqw)){
                result.add(KR.getIdKnowledge());
            }
        }
        catch (Exception e){
            throw new RuntimeException("未找到对应知识点");
        }
        return result;
    }

    public IPage<KnowledgesResources> findResourcePageByKnowledgeId(int current,int size,Long KnowledgeId){
        IPage<KnowledgesResources> result = new Page<>(current,size);
        IPage<KnowledgesResources> returnPage = null;
        LambdaQueryWrapper<KnowledgesResources> lqw = new LambdaQueryWrapper<>();
        lqw.like(KnowledgesResources::getIdKnowledge,KnowledgeId);
        try{
            returnPage = getBaseMapper().selectPage(result,lqw);
        }
        catch (Exception e){
            throw new UserServiceException("查询失败");
        }
        return returnPage;
    }
}
