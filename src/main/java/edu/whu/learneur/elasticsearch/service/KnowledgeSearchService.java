package edu.whu.learneur.elasticsearch.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.elasticsearch.dao.KnowledgeEsRepository;
import edu.whu.learneur.elasticsearch.entity.KnowledgeEs;
import edu.whu.learneur.elasticsearch.entity.ResourceEs;
import edu.whu.learneur.elasticsearch.utils.ConvertUtil;
import edu.whu.learneur.resource.entity.*;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import edu.whu.learneur.elasticsearch.service.ResourceSearchService.ResourceType;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Setter(onMethod_ = @Autowired)
public class KnowledgeSearchService {
    private KnowledgeEsRepository knowledgeEsRepository;

    public Page<Long> search(String keyword, Pageable pageable) {
        return knowledgeEsRepository.matchKnowledge(keyword, pageable)
                .map(hit -> hit.getContent().getId());
    }

    public IPage<Long> search(String keyword, int pageIndex, int pageSize) {
        IPage<Long> mybatisPlusPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageIndex, pageSize);
        Page<Long> springDataPage = search(keyword, PageRequest.of(pageIndex, pageSize));
        mybatisPlusPage.setTotal(springDataPage.getTotalElements());
        mybatisPlusPage.setRecords(springDataPage.getContent());
        return mybatisPlusPage;
    }

    public void addKnowledgeResource(Long knowledgeId, ResourceEs resourceEs) {
        knowledgeEsRepository.addKnowledgeResource(knowledgeId, resourceEs);
    }

    public void addKnowledgeResources(Long knowledgeId, List<ResourceEs> resourceEsList) {
        knowledgeEsRepository.addknowledgeResources(knowledgeId, resourceEsList);
    }

    public void removeKnowledgeResource(Long knowledgeId, Short resourceType, Long resourceId) {
        ResourceEs resourceEs = ResourceEs.builder()
                .resourceType(resourceType)
                .resourceId(resourceId)
                .build();
        knowledgeEsRepository.removeKnowledgeResource(knowledgeId, resourceEs);
    }

    public void removeKnowledgeResources(Long knowledgeId, Short resourceType, List<Long> resourceIdList) {
        List<ResourceEs> resourceEsList = resourceIdList.stream()
                .map(resourceId -> ResourceEs.builder()
                        .resourceType(resourceType)
                        .resourceId(resourceId)
                        .build())
                .collect(Collectors.toList());
        knowledgeEsRepository.removeKnowledgeResources(knowledgeId, resourceEsList);
    }

    public void save(Knowledge knowledge) {
        knowledgeEsRepository.save(new KnowledgeEs(knowledge.getId(), knowledge.getKnowledgeName(), knowledge.getKnowledgeDescription()));
    }

    public void updateResource(ResourceEs resourceEs) {
        knowledgeEsRepository.updateResource(resourceEs);
    }

    public void deleteResource(Short resourceType, Long resourceId) {
        ResourceEs resourceEs = ResourceEs.builder()
                .resourceType(resourceType)
                .resourceId(resourceId)
                .build();
        knowledgeEsRepository.deleteResource(resourceEs);
    }

    public void delete(Long knowledgeId) {
        knowledgeEsRepository.deleteById(knowledgeId);
    }

    public void addKnowledgeBook(Long knowledgeId, Book book) {
        addKnowledgeResource(knowledgeId, ConvertUtil.BookToResourceEs(book));
    }

    public void addKnowledgeBooks(Long knowledgeId, List<Book> books) {
        addKnowledgeResources(knowledgeId, books.stream()
                .map(ConvertUtil::BookToResourceEs).collect(Collectors.toList()));
    }

    public void addKnowledgeLesson(Long knowledgeId, Lesson lesson) {
        addKnowledgeResource(knowledgeId, ConvertUtil.LessonToResourceEs(lesson));
    }

    public void addKnowledgeLessons(Long knowledgeId, List<Lesson> lessons) {
        addKnowledgeResources(knowledgeId, lessons.stream()
                .map(ConvertUtil::LessonToResourceEs).collect(Collectors.toList()));
    }

    public void addKnowledgeProject(Long knowledgeId, Project project) {
        addKnowledgeResource(knowledgeId, ConvertUtil.ProjectToResourceEs(project));
    }

    public void addKnowledgeProjects(Long knowledgeId, List<Project> projects) {
        addKnowledgeResources(knowledgeId, projects.stream()
                .map(ConvertUtil::ProjectToResourceEs).collect(Collectors.toList()));
    }

    public void addKnowledgeVideo(Long knowledgeId, Video video) {
        addKnowledgeResource(knowledgeId, ConvertUtil.VideoToResourceEs(video));
    }

    public void addKnowledgeVideos(Long knowledgeId, List<Video> videos) {
        addKnowledgeResources(knowledgeId, videos.stream()
                .map(ConvertUtil::VideoToResourceEs).collect(Collectors.toList()));
    }

    public void addKnowledgeTutorial(Long knowledgeId, Tutorial tutorial) {
        addKnowledgeResource(knowledgeId, ConvertUtil.TutorialToResourceEs(tutorial));
    }

    public void addKnowledgeTutorials(Long knowledgeId, List<Tutorial> tutorials) {
        addKnowledgeResources(knowledgeId, tutorials.stream()
                .map(ConvertUtil::TutorialToResourceEs).collect(Collectors.toList()));
    }

    public void removeKnowledgeBook(Long knowledgeId, Long bookId) {
        removeKnowledgeResource(knowledgeId, ResourceType.Book, bookId);
    }

    public void removeKnowledgeBooks(Long knowledgeId, List<Long> bookIdList) {
        removeKnowledgeResources(knowledgeId, ResourceType.Book, bookIdList);
    }

    public void removeKnowledgeLesson(Long knowledgeId, Long lessonId) {
        removeKnowledgeResource(knowledgeId, ResourceType.Lesson, lessonId);
    }

    public void removeKnowledgeLessons(Long knowledgeId, List<Long> lessonIdList) {
        removeKnowledgeResources(knowledgeId, ResourceType.Lesson, lessonIdList);
    }

    public void removeKnowledgeProject(Long knowledgeId, Long projectId) {
        removeKnowledgeResource(knowledgeId, ResourceType.Project, projectId);
    }

    public void removeKnowledgeProjects(Long knowledgeId, List<Long> projectIdList) {
        removeKnowledgeResources(knowledgeId, ResourceType.Project, projectIdList);
    }

    public void removeKnowledgeVideo(Long knowledgeId, Long videoId) {
        removeKnowledgeResource(knowledgeId, ResourceType.Video, videoId);
    }

    public void removeKnowledgeVideos(Long knowledgeId, List<Long> videoIdList) {
        removeKnowledgeResources(knowledgeId, ResourceType.Video, videoIdList);
    }

    public void removeKnowledgeTutorial(Long knowledgeId, Long tutorialId) {
        removeKnowledgeResource(knowledgeId, ResourceType.Tutorial, tutorialId);
    }

    public void removeKnowledgeTutorials(Long knowledgeId, List<Long> tutorialIdList) {
        removeKnowledgeResources(knowledgeId, ResourceType.Tutorial, tutorialIdList);
    }

    public void removeKnowledgeLesson(Long knowledgeId, Lesson lesson) {
        removeKnowledgeResource(knowledgeId, ResourceType.Lesson, lesson.getId());
    }

    public void removeKnowledgeVideo(Long knowledgeId, Video video) {
        removeKnowledgeResource(knowledgeId, ResourceType.Video, video.getId());
    }

    public void removeKnowledgeTutorial(Long knowledgeId, Tutorial tutorial) {
        removeKnowledgeResource(knowledgeId, ResourceType.Tutorial, tutorial.getId());
    }

    public void removeKnowledgeProject(Long knowledgeId, Project project) {
        removeKnowledgeResource(knowledgeId, ResourceType.Project, project.getIdProject());
    }

    public void updateBook(Book book) {
        updateResource(ConvertUtil.BookToResourceEs(book));
    }

    public void updateLesson(Lesson lesson) {
        updateResource(ConvertUtil.LessonToResourceEs(lesson));
    }

    public void updateVideo(Video video) {
        updateResource(ConvertUtil.VideoToResourceEs(video));
    }

    public void updateTutorial(Tutorial tutorial) {
        updateResource(ConvertUtil.TutorialToResourceEs(tutorial));
    }

    public void updateProject(Project project) {
        updateResource(ConvertUtil.ProjectToResourceEs(project));
    }

    public void deleteBook(Long bookId) {
        deleteResource(ResourceType.Book, bookId);
    }

    public void deleteLesson(Long lessonId) {
        deleteResource(ResourceType.Lesson, lessonId);
    }

    public void deleteVideo(Long videoId) {
        deleteResource(ResourceType.Video, videoId);
    }

    public void deleteTutorial(Long tutorialId) {
        deleteResource(ResourceType.Tutorial, tutorialId);
    }

    public void deleteProject(Long projectId) {
        deleteResource(ResourceType.Project, projectId);
    }


}
