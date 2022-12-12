package edu.whu.learneur.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.resource.crawler.github.ProjectCrawler;
import edu.whu.learneur.resource.entity.Book;
import edu.whu.learneur.resource.entity.Project;
import edu.whu.learneur.resource.service.IBookService;
import edu.whu.learneur.resource.service.IProjectService;
import edu.whu.learneur.resource.service.serviceImpl.BookServiceImpl;
import edu.whu.learneur.resource.service.serviceImpl.ProjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class CrawlTest {
    @Autowired
    private IBookService bookService;

    @Autowired
    private ProjectCrawler projectCrawler;

    @Autowired
    private IProjectService projectService;

    @Test
    public void projectTest() {
        List<Project> res = projectCrawler.crawl("python");
        projectService.addProjects(res);
        IPage<Project> page = projectService.findAllProjects(0, 30);
        System.out.println(page.getPages());
        for(Project project: page.getRecords()) {
            System.out.println(project);
        }
    }
    @Test
    public void bookTest(){
        IPage<Book> res = bookService.findBookPage(123L,0,2);
        for(Book book: res.getRecords()) {
            System.out.println(book);
        }
    }
}
