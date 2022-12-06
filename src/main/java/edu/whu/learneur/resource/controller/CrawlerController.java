package edu.whu.learneur.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Knowledge;
import edu.whu.learneur.resource.crawler.Itheima.ItheimaCrawler;
import edu.whu.learneur.resource.crawler.bilibili.VideoCrawler;
import edu.whu.learneur.resource.crawler.github.ProjectCrawler;
import edu.whu.learneur.resource.crawler.runoob.RunoobCrawler;
import edu.whu.learneur.resource.crawler.zlib.BookCrawler;
import edu.whu.learneur.resource.entity.*;
import edu.whu.learneur.resource.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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


    public void crawlAll(){

        List<Knowledge> list= knowledgeService.findAll();
        for(Knowledge knowledge : list) {
            try
            {
                crawlByKnowledge(knowledge);
            }
            catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }

    }

    @Transactional
    public void crawlByKnowledge(Knowledge knowledge) throws Exception {
        String name = knowledge.getKnowledgeName();
        Long id = knowledge.getId();
        // TODO: 并发
        List<Book> books = bookCrawler.crawl(name);
        bookService.addBooks(books);
        krService.addBook(id, books);
        List<Project> projects = projectCrawler.crawl(name);
        projectService.addProjects(projects);
        krService.addProject(id, projects);
        List<Lesson> lessons = itheimaCrawler.crawl(name);
        lessonService.addBooks(lessons);
        krService.addLesson(id, lessons);
        List<Tutorial> tutorials = runoobCrawler.crawl(name);
        tutorialService.addTutorial(tutorials);
        krService.addTutorial(id, tutorials);
        List<Video> videos = videoCrawler.crawl(name);
        videoService.addVideos(videos);
        krService.addVideo(id, videos);



    }



}
