package edu.whu.learneur.resource.controller;

import edu.whu.learneur.elasticsearch.service.KnowledgeSearchService;
import edu.whu.learneur.elasticsearch.service.ResourceSearchService;
import edu.whu.learneur.resource.crawler.Itheima.ItheimaCrawler;
import edu.whu.learneur.resource.crawler.bilibili.VideoCrawler;
import edu.whu.learneur.resource.crawler.github.ProjectCrawler;
import edu.whu.learneur.resource.crawler.runoob.RunoobCrawler;
import edu.whu.learneur.resource.crawler.zlib.BookCrawler;
import edu.whu.learneur.resource.entity.*;
import edu.whu.learneur.resource.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crawler")
public class CrawlerController {
    @Autowired
    private BookCrawler bookCrawler;
    @Autowired
    private RunoobCrawler runoobCrawler;
    @Autowired
    private VideoCrawler videoCrawler;
    @Autowired
    private ProjectCrawler projectCrawler;
    @Autowired
    private ItheimaCrawler itheimaCrawler;

    @Autowired
    private IKnowledgeService knowledgeService;
    @Autowired
    private IBookService bookService;
    @Autowired
    private IVideoService videoService;
    @Autowired
    private ILessonService lessonService;
    @Autowired
    private ITutorialService tutorialService;
    @Autowired
    private IProjectService projectService;

    @Autowired
    private ResourceSearchService resourceSearchService;

    @Autowired
    private KnowledgeSearchService knowledgeSearchService;


    @Scheduled(cron = "${time.cron}")
    public void crawlVideos() {
        List<Knowledge> list= knowledgeService.findAll();
        for(Knowledge knowledge : list) {
            List<Video> res = videoCrawler.crawl(knowledge.getKnowledgeName());
            res = videoService.addVideos(res,knowledge.getIdKnowledge());
            res.forEach(resourceSearchService::save);
            knowledgeSearchService.addKnowledgeVideos(knowledge.getIdKnowledge(), res);
            //krService.addVideo(knowledge.getIdKnowledge(), res);
        }
    }

    @Scheduled(cron = "${time.test}")
    public void crawlProjects() {
        List<Knowledge> list = knowledgeService.findAll();
        for(Knowledge knowledge: list) {
            List<Project> res = projectCrawler.crawl(knowledge.getKnowledgeName());
            res = projectService.addProjects(res , knowledge.getIdKnowledge());
            res.forEach(resourceSearchService::save);
            knowledgeSearchService.addKnowledgeProjects(knowledge.getIdKnowledge(), res);
        }
    }

    @Scheduled(cron = "${time.cron}")
    public void crawlLessons() {
        List<Knowledge> list = knowledgeService.findAll();
        try{
            for(Knowledge knowledge: list) {
                List<Lesson> res = itheimaCrawler.crawl(knowledge.getKnowledgeName());
                res = lessonService.addLessons(res, knowledge.getIdKnowledge());
                res.forEach(resourceSearchService::save);
                knowledgeSearchService.addKnowledgeLessons(knowledge.getIdKnowledge(), res);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Scheduled(cron = "${time.cron}")
    public void crawlTutorials() {
        List<Knowledge> list = knowledgeService.findAll();
        try{
            for(Knowledge knowledge: list) {
                List<Tutorial> res = runoobCrawler.crawl(knowledge.getKnowledgeName());
                res = tutorialService.addTutorial(res, knowledge.getIdKnowledge());
                res.forEach(resourceSearchService::save);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Scheduled(cron = "${time.cron}")
    public void crawlBooks() {
        List<Knowledge> list = knowledgeService.findAll();
        System.out.println(list.size());
        try{
            for(Knowledge knowledge: list) {
                System.out.println("------"+knowledge.getIdKnowledge());
                List<Book> res = bookCrawler.crawl(knowledge.getKnowledgeName());
                res = bookService.addBooks(res, knowledge.getIdKnowledge());
                res.forEach(resourceSearchService::save);
                knowledgeSearchService.addKnowledgeBooks(knowledge.getIdKnowledge(), res);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
