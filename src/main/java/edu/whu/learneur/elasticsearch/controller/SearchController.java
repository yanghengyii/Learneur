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
import org.springframework.web.bind.annotation.*;
import edu.whu.learneur.elasticsearch.service.ResourceSearchService.ResourceType;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/search")
@Setter(onMethod_ = @Autowired)
@CrossOrigin
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
        if (ids.isEmpty()) {
            return emptyPage();
        }
        List<Knowledge> knowledgeList = knowledgeService.listByIds(ids.getContent());
        IPage<Knowledge> page = new Page<>(pageIndex, pageSize, ids.getTotalElements());
        page.setRecords(knowledgeList);
        return page;
    }

    @GetMapping("/lessons")
    public IPage<Lesson> searchLessons(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                       @RequestParam(value = "current", defaultValue = "0") int pageIndex,
                                       @RequestParam(value = "size", defaultValue = "15") int pageSize) {
        org.springframework.data.domain.Page<Long> lessonIds = resouceSearchService.search(keyword, ResourceType.Lesson, PageRequest.of(pageIndex, pageSize));
        if(lessonIds.isEmpty()) return emptyPage();
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
        if (videoIds.isEmpty()) return emptyPage();
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
        if (tutorialIds.isEmpty()) return emptyPage();
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
        if (projectIds.isEmpty()) return emptyPage();
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
        if (ids.isEmpty()) return emptyPage();
        List<Notes> noteList = notesService.listByIds(ids.getContent());
        IPage<Notes> page = new Page<>(pageIndex, pageSize, ids.getTotalElements());
        page.setRecords(noteList);
        return page;
    }

    @GetMapping("/books")
    public IPage<Book> searchBooks(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                   @RequestParam(value = "current", defaultValue = "0") int pageIndex,
                                   @RequestParam(value = "size", defaultValue = "15") int pageSize) {
        org.springframework.data.domain.Page<Long> ids = resouceSearchService.search(keyword, ResourceType.Book, PageRequest.of(pageIndex, pageSize));
        if (ids.isEmpty()) return emptyPage();
        List<Book> bookList = bookService.listByIds(ids.getContent());
        IPage<Book> page = new Page<>(pageIndex, pageSize, ids.getTotalElements());
        page.setRecords(bookList);
        return page;
    }

    private <T> IPage<T> emptyPage() {
        return new Page<T>(0, 0 ,0);
    }
}
