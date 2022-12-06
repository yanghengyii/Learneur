package edu.whu.learneur.resource.controller;

import edu.whu.learneur.domain.Knowledge;
import edu.whu.learneur.resource.crawler.Itheima.ItheimaCrawler;
import edu.whu.learneur.resource.crawler.bilibili.VideoCrawler;
import edu.whu.learneur.resource.crawler.github.ProjectCrawler;
import edu.whu.learneur.resource.crawler.runoob.RunoobCrawler;
import edu.whu.learneur.resource.crawler.zlib.BookCrawler;
import edu.whu.learneur.resource.entity.Book;
import edu.whu.learneur.resource.entity.Project;
import edu.whu.learneur.resource.service.*;
import edu.whu.learneur.service.IProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.tree.ExpandVetoException;
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
    private IProjectsService projectsService;


    public void crawlAll(){



    }

    @Transactional
    public void crawlByKnowledge(Knowledge knowledge) throws Exception {
        String name = knowledge.getKnowledgeName();
        List<Book> books = bookCrawler.crawl(name);
        bookService.addBooks(books);
        List<Project> projects = projectCrawler.crawl(name);

    }



}
