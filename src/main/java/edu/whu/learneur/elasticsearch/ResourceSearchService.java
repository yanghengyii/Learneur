package edu.whu.learneur.elasticsearch;

import edu.whu.learneur.elasticsearch.dao.KnowledgeEsRepository;
import edu.whu.learneur.elasticsearch.dao.ResourceEsRepository;
import edu.whu.learneur.elasticsearch.entity.ResourceEs;
import edu.whu.learneur.resource.entity.*;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

@Service
@Setter(onMethod_ = @Autowired)
public class ResourceSearchService {

    private ResourceEsRepository resourceEsRepository;

    private KnowledgeEsRepository knowledgeEsRepository;

    public Page<Long> search(String keyword, int type, Pageable pageable) {
        if (type == 0) {
            return resourceEsRepository.matchAllTypeResource(keyword, pageable)
                    .map(this::getResourceIdFromSearchHit);
        } else {
            return resourceEsRepository.matchSpecificTypeResource(type, keyword, pageable)
                    .map(this::getResourceIdFromSearchHit);
        }
    }
    public void save(Short resourceType, Long resourceId, String name, String description) {
        ResourceEs existedResource = resourceEsRepository.
                findByResourceTypeAndResourceId(resourceType, resourceId);
        String id = existedResource == null ? null : existedResource.getId();
        ResourceEs resourceEs = ResourceEs.builder()
                .id(id)
                .resourceId(resourceId)
                .resourceType(resourceType)
                .name(name)
                .description(description)
                .build();
        resourceEsRepository.save(resourceEs);
    }

    public void delete(Short resourceType, Long resourceId) {
        resourceEsRepository.deleteByResourceTypeAndResourceId(resourceType, resourceId);
    }

    public void save(Book book) {
        save(ResourceType.Book, book.getId(), book.getTitle(), null);
    }

    public void save(Lesson lesson) {
        save(ResourceType.Lesson, lesson.getId(), lesson.getTitle(), lesson.getDescription());
    }

    public void save(Project project) {
        save(ResourceType.Project, project.getIdProject(), project.getName(), project.getDescription());
    }

    public void save(Tutorial tutorial) {
        save(ResourceType.Tutorial, tutorial.getId(), tutorial.getName(), tutorial.getSummary());
    }

    public void save(Video video) {
        save(ResourceType.Video, video.getId(), video.getTitle(), video.getDescription());
    }

    public void delete(Book book) {
        delete(ResourceType.Book, book.getId());
    }

    public void delete(Lesson lesson) {
        delete(ResourceType.Lesson, lesson.getId());
    }

    public void delete(Project project) {
        delete(ResourceType.Project, project.getIdProject());
    }

    public void delete(Tutorial tutorial) {
        delete(ResourceType.Tutorial, tutorial.getId());
    }

    public void delete(Video video) {
        delete(ResourceType.Video, video.getId());
    }

    private Long getResourceIdFromSearchHit(SearchHit<ResourceEs> hit) {
        return hit.getContent().getResourceId();
    }

    public static class ResourceType {
        public static final Short Lesson = 1;
        public static final Short Book = 2;
        public static final Short Project = 3;
        public static final Short Tutorial = 4;
        public static final Short Video = 5;
    }
}
