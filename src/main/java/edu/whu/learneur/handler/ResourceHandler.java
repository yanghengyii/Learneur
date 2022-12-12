package edu.whu.learneur.handler;

import edu.whu.learneur.elasticsearch.service.ResourceSearchService;
import edu.whu.learneur.resource.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResourceHandler {
    @Autowired
    private ResourceSearchService service;

    @EventListener
    @Async
    public void handleProjectEvent(Project project) {
        service.save(project);
    }

    @EventListener
    @Async
    public void handleBookEvent(Book book) {
        service.save(book);
    }

    @EventListener
    @Async
    public void handleVideoEvent(Video video) {
        service.save(video);
    }

    @EventListener
    @Async
    public void handleLessonEvent(Lesson lesson) {
        service.save(lesson);
    }

    @EventListener
    @Async
    public void handleTutorialEvent(Tutorial tutorial) {
        service.save(tutorial);
    }


}
