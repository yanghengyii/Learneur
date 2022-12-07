package edu.whu.learneur.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.resource.crawler.Itheima.ItheimaCrawler;
import edu.whu.learneur.resource.crawler.bilibili.VideoCrawler;
import edu.whu.learneur.resource.crawler.github.ProjectCrawler;
import edu.whu.learneur.resource.crawler.runoob.RunoobCrawler;
import edu.whu.learneur.resource.crawler.zlib.BookCrawler;
import edu.whu.learneur.resource.entity.*;
import edu.whu.learneur.resource.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
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
    private IKRService krService;
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

    @Scheduled(cron = "${time.cron}")
    public void crawlVideos() {
        List<Knowledge> list= knowledgeService.findAll();
        for(Knowledge knowledge : list) {
            List<Video> res = videoCrawler.crawl(knowledge.getKnowledgeName());
            videoService.addVideos(res);
        }
    }

    @Scheduled(cron = "${time.cron}")
    public void crawlProjects() {
        List<Knowledge> list = knowledgeService.findAll();
        for(Knowledge knowledge: list) {
            List<Project> res = projectCrawler.crawl(knowledge.getKnowledgeName());
            projectService.addProjects(res);
        }
    }

    @Scheduled(cron = "${time.cron}")
    public void crawlLessons() {
        List<Knowledge> list = knowledgeService.findAll();
        try{
            for(Knowledge knowledge: list) {
                List<Lesson> res = itheimaCrawler.crawl(knowledge.getKnowledgeName());
                lessonService.addLessons(res);
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
                tutorialService.addTutorial(res);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Scheduled(cron = "${time.cron}")
    public void crawlBooks() {
        List<Knowledge> list = knowledgeService.findAll();
        try{
            for(Knowledge knowledge: list) {
                List<Book> res = bookCrawler.crawl(knowledge.getKnowledgeName());
                bookService.addBooks(res);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



}
