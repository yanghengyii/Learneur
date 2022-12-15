package edu.whu.learneur.elasticsearch;

import edu.whu.learneur.domain.Notes;
import edu.whu.learneur.elasticsearch.service.KnowledgeSearchService;
import edu.whu.learneur.elasticsearch.service.NoteSearchService;
import edu.whu.learneur.elasticsearch.service.ResourceSearchService;
import edu.whu.learneur.elasticsearch.service.ResourceSearchService.ResourceType;
import edu.whu.learneur.resource.entity.*;
import edu.whu.learneur.resource.service.*;
import edu.whu.learneur.service.INotesService;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Setter(onMethod_ = @Autowired)
public class SyncIndexTest {
    KnowledgeSearchService knowledgeSearchService;
    ResourceSearchService resourceSearchService;
    NoteSearchService noteSearchService;

    IKnowledgeService knowledgeService;
    IBookService bookService;
    IProjectService projectService;
    IVideoService videoService;
    ITutorialService tutorialService;
    ILessonService lessonService;
    INotesService noteService;

    IKRService krService;

    @Test
    void syncIndex() {
        List<Knowledge> knowledges = knowledgeService.findAll();
        knowledges.forEach(knowledgeSearchService::save);
        List<Book> books = bookService.list();
        books.forEach(resourceSearchService::save);
        List<Lesson> lessons = lessonService.list();
        lessons.forEach(resourceSearchService::save);
        List<Video> videos = videoService.list();
        videos.forEach(resourceSearchService::save);
        List<Tutorial> tutorials = tutorialService.list();
        tutorials.forEach(resourceSearchService::save);
        List<Project> projects = projectService.list();
        projects.forEach(resourceSearchService::save);
        List<Notes> notes = noteService.list();
        notes.forEach(noteSearchService::save);

        List<KnowledgeResource> krs = krService.list();
        krs.forEach(kr -> {
            int type = kr.getType();
            if (type == ResourceType.Book) {
                Book book = bookService.getById(kr.getIdResource());
                knowledgeSearchService.addKnowledgeBook(kr.getIdKnowledge(), book);
            } else if (type == ResourceType.Project) {
                Project project = projectService.getById(kr.getIdResource());
                knowledgeSearchService.addKnowledgeProject(kr.getIdKnowledge(), project);
            } else if (type == ResourceType.Video) {
                Video video = videoService.getById(kr.getIdResource());
                knowledgeSearchService.addKnowledgeVideo(kr.getIdKnowledge(), video);
            } else if (type == ResourceType.Tutorial) {
                Tutorial tutorial = tutorialService.getById(kr.getIdResource());
                knowledgeSearchService.addKnowledgeTutorial(kr.getIdKnowledge(), tutorial);
            } else if (type == ResourceType.Lesson) {
                Lesson lesson = lessonService.getById(kr.getIdResource());
                knowledgeSearchService.addKnowledgeLesson(kr.getIdKnowledge(), lesson);
            }
        });
    }

}
