package edu.whu.learneur.elasticsearch.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.domain.Notes;
import edu.whu.learneur.elasticsearch.service.KnowledgeSearchService;
import edu.whu.learneur.elasticsearch.service.NoteSearchService;
import edu.whu.learneur.elasticsearch.service.ResourceSearchService;
import edu.whu.learneur.resource.entity.*;
import edu.whu.learneur.resource.service.*;
import edu.whu.learneur.service.INotesService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.whu.learneur.elasticsearch.service.ResourceSearchService.ResourceType;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/search")
@Setter(onMethod_ = @Autowired)
public class SearchController {

    private ResourceSearchService resouceSearchService;
    private KnowledgeSearchService knowledgeSearchService;
    private NoteSearchService noteSearchService;

    private IKnowledgeService knowledgeService;
    private ILessonService lessonService;
    private IBookService bookService;
    private IVideoService videoService;
    private ITutorialService tutorialService;
    private IProjectService projectService;
    private INotesService notesService;

    @GetMapping("/knowledges")
    public IPage<Knowledge> searchKnowledges(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                             @RequestParam(value = "current", defaultValue = "0") int pageIndex,
                                             @RequestParam(value = "size", defaultValue = "15") int pageSize) {
        org.springframework.data.domain.Page<Long> ids = knowledgeSearchService.search(keyword, PageRequest.of(pageIndex, pageSize));
        List<Knowledge> knowledgeList = knowledgeService.listByIds(ids.getContent());
        IPage<Knowledge> page = new Page<>(pageIndex, pageSize, ids.getTotalElements());
        page.setRecords(knowledgeList);
        return page;
    }

    @GetMapping("/books")
    public IPage<Book> searchBooks(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                   @RequestParam(value = "current", defaultValue = "0") int pageIndex,
                                   @RequestParam(value = "size", defaultValue = "15") int pageSize) {
//        org.springframework.data.domain.Page<Long> bookIds = resouceSearchService.search(keyword, ResourceType.Book, PageRequest.of(pageIndex, pageSize));
//
//        List<Book> books =  bookService.listByIds(bookIds.getContent());
//
//        IPage<Book> bookPage = new Page<>(pageIndex, pageSize, bookIds.getTotalElements());
//        bookPage.setRecords(books);
//        return bookPage;
        // for test
        IPage<Book> page = new Page<>(0, 10, 200);
        Book book = new Book();
        Knowledge knowledge = new Knowledge();
        knowledge.setKnowledgeName("test");
        book.setId(1L);
        book.setTitle("test");
        book.setAuthor("test");
        book.setPublisher("test");
        book.setPublishDate("test");
        book.setKnowledge(Collections.singletonList(knowledge));
        book.setCoverUrl("test");
        book.setLanguage("test");
        book.setFileSize("test");
        book.setFileType("test");
        page.setRecords(Collections.nCopies(15,book));
        return page;
    }

    @GetMapping("/lessons")
    public IPage<Lesson> searchLessons(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                       @RequestParam(value = "current", defaultValue = "0") int pageIndex,
                                       @RequestParam(value = "size", defaultValue = "15") int pageSize) {
        org.springframework.data.domain.Page<Long> lessonIds = resouceSearchService.search(keyword, ResourceType.Lesson, PageRequest.of(pageIndex, pageSize));

        List<Lesson> lessons =  lessonService.listByIds(lessonIds.getContent());

        IPage<Lesson> lessonPage = new Page<>(pageIndex, pageSize, lessonIds.getTotalElements());
        lessonPage.setRecords(lessons);
        return lessonPage;
    }

    @GetMapping("/videos")
    public IPage<Video> searchVideos(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                     @RequestParam(value = "current", defaultValue = "0") int pageIndex,
                                     @RequestParam(value = "size", defaultValue = "15") int pageSize) {
        org.springframework.data.domain.Page<Long> videoIds = resouceSearchService.search(keyword, ResourceType.Video, PageRequest.of(pageIndex, pageSize));

        List<Video> videos =  videoService.listByIds(videoIds.getContent());

        IPage<Video> videoPage = new Page<>(pageIndex, pageSize, videoIds.getTotalElements());
        videoPage.setRecords(videos);
        return videoPage;
    }

    @GetMapping("/tutorials")
    public IPage<Tutorial> searchTutorials(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                           @RequestParam(value = "current", defaultValue = "0") int pageIndex,
                                           @RequestParam(value = "size", defaultValue = "15") int pageSize) {
        org.springframework.data.domain.Page<Long> tutorialIds = resouceSearchService.search(keyword, ResourceType.Tutorial, PageRequest.of(pageIndex, pageSize));

        List<Tutorial> tutorials =  tutorialService.listByIds(tutorialIds.getContent());

        IPage<Tutorial> tutorialPage = new Page<>(pageIndex, pageSize, tutorialIds.getTotalElements());
        tutorialPage.setRecords(tutorials);
        return tutorialPage;
    }

    @GetMapping("/projects")
    public IPage<Project> searchProjects(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                         @RequestParam(value = "current", defaultValue = "0") int pageIndex,
                                         @RequestParam(value = "size", defaultValue = "15") int pageSize) {
        org.springframework.data.domain.Page<Long> projectIds = resouceSearchService.search(keyword, ResourceType.Project, PageRequest.of(pageIndex, pageSize));

        List<Project> projects =  projectService.listByIds(projectIds.getContent());

        IPage<Project> projectPage = new Page<>(pageIndex, pageSize, projectIds.getTotalElements());
        projectPage.setRecords(projects);
        return projectPage;
    }

    @GetMapping("/notes")
    public IPage<Notes> searchNotes(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                    @RequestParam(value = "current", defaultValue = "0") int pageIndex,
                                    @RequestParam(value = "size", defaultValue = "15") int pageSize) {
        org.springframework.data.domain.Page<Long> ids = noteSearchService.search(keyword, PageRequest.of(pageIndex, pageSize));
        List<Notes> noteList = notesService.listByIds(ids.getContent());
        IPage<Notes> page = new Page<>(pageIndex, pageSize, ids.getTotalElements());
        page.setRecords(noteList);
        return page;
    }

}
