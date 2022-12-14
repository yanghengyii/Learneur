package edu.whu.learneur.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.domain.Notes;
import edu.whu.learneur.resource.entity.*;
import edu.whu.learneur.resource.service.*;
import edu.whu.learneur.service.INotesService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin
@Setter(onMethod_ = @Autowired)
public class TempController {
@GetMapping("/hello")
public String hello() {
return "hello";
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
        IPage<Book> page = new Page<>(0, 15, 200);
        Book book = new Book();
        Knowledge knowledge = new Knowledge();
        knowledge.setKnowledgeName("tag");
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

    @GetMapping("/knowledges")
    public IPage<Knowledge> searchKnowledges(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                             @RequestParam(value = "current", defaultValue = "0") int pageIndex,
                                             @RequestParam(value = "size", defaultValue = "15") int pageSize) {
        IPage<Knowledge> page = new Page<>(0, 10, 140);
        Knowledge knowledge = new Knowledge();
        knowledge.setId(1L);
        knowledge.setKnowledgeName("test");
        knowledge.setKnowledgeDescription("testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest");
        page.setRecords(Collections.nCopies(10,knowledge));
        return page;
    }

    @GetMapping("/videos")
    public IPage<Video> searchVideos(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                     @RequestParam(value = "current", defaultValue = "0") int pageIndex,
                                     @RequestParam(value = "size", defaultValue = "15") int pageSize) {
        IPage<Video> page = new Page<>(0, 10, 140);
        Video video = new Video();
        Knowledge knowledge = new Knowledge();
        knowledge.setKnowledgeName("test");
        video.setId(1L);
        video.setTitle("test");
        video.setAuthor("test");
        video.setPic("test");
        video.setDescription("testtesttesttesttesttesttesttestetstetstettstettstet");
        video.setLength("test");
        video.setBVid("test");
        video.setKnowledge(Collections.singletonList(knowledge));
        page.setRecords(Collections.nCopies(10,video));
        return page;
    }

    @GetMapping("/projects")
    public IPage<Project> searchProjects(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                         @RequestParam(value = "current", defaultValue = "0") int pageIndex,
                                         @RequestParam(value = "size", defaultValue = "15") int pageSize) {
        IPage<Project> page = new Page<>(0, 10, 140);
        Project project = new Project();
        Knowledge knowledge = new Knowledge();
        knowledge.setKnowledgeName("test");
        project.setIdProject(1L);
        project.setName("test");
        project.setLanguage("test");
        project.setForks(1000);
        project.setDescription("testtesttesttesttesttesttesttestetstetstettstettstet");
        project.setLink("test");
        project.setHomePage("test");
        project.setReadme("test");
        project.setUpdateTime(LocalDate.now());
        project.setStarGazers(100);
        project.setKnowledge(Collections.singletonList(knowledge));
        page.setRecords(Collections.nCopies(10,project));
        return page;
    }

    @GetMapping("/notes")
    public IPage<Notes> searchNotes(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                    @RequestParam(value = "current", defaultValue = "0") int pageIndex,
                                    @RequestParam(value = "size", defaultValue = "15") int pageSize) {
        IPage<Notes> page = new Page<>(0, 10, 140);
        Notes notes = new Notes();
        notes.setNoteId(1L);
        notes.setNoteTitle("test");
        notes.setNotePreviewContent("testtesttesttesttesttesttesttestetstetstettstettstet");
        notes.setNoteContent("testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest");
        page.setRecords(Collections.nCopies(10,notes));
        return page;
    }



}
