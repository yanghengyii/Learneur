package edu.whu.learneur.elasticsearch.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.override.MybatisMapperMethod;
import edu.whu.learneur.elasticsearch.dao.KnowledgeEsRepository;
import edu.whu.learneur.elasticsearch.dao.ResourceEsRepository;
import edu.whu.learneur.elasticsearch.entity.ResourceEs;
import edu.whu.learneur.elasticsearch.utils.ConvertUtil;
import edu.whu.learneur.resource.entity.*;
import lombok.Setter;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Setter(onMethod_ = @Autowired)
public class ResourceSearchService {

    private ResourceEsRepository resourceEsRepository;

    private KnowledgeSearchService knowledgeSearchService;

    public Page<Long> search(String keyword, int type, Pageable pageable) {
        return resourceEsRepository.matchSpecificTypeResource(type, keyword, pageable)
                    .map(this::getresourceIdFromSearchHit);
    }
    public IPage<Long> search(String keyword, int type, int pageIndex, int pageSize) {
        IPage<Long> mybatisPlusPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageIndex, pageSize);
        Page<Long> springDataPage = search(keyword, type, PageRequest.of(pageIndex, pageSize));
        mybatisPlusPage.setTotal(springDataPage.getTotalElements());
        mybatisPlusPage.setRecords(springDataPage.getContent());
        return mybatisPlusPage;
    }
    public void save(ResourceEs resourceEs) {
        knowledgeSearchService.updateResource(resourceEs);
        ResourceEs existedResource = resourceEsRepository.
                findByResourceTypeAndResourceId(resourceEs.getResourceType(), resourceEs.getResourceId());
        if (existedResource != null) {
            resourceEs.setId(existedResource.getId());
        }
        resourceEsRepository.save(resourceEs);
    }

    public void delete(Short resourceType, Long resourceId) {
        knowledgeSearchService.deleteResource(resourceType, resourceId);
        resourceEsRepository.deleteByResourceTypeAndResourceId(resourceType, resourceId);
    }

    public void save(Book book) {
        save(ConvertUtil.BookToResourceEs(book));
    }

    public void save(Lesson lesson) {
        save(ConvertUtil.LessonToResourceEs(lesson));
    }

    public void save(Video video) {
        save(ConvertUtil.VideoToResourceEs(video));
    }

    public void save(Project project) {
        save(ConvertUtil.ProjectToResourceEs(project));
    }

    public void save(Tutorial tutorial) {
        save(ConvertUtil.TutorialToResourceEs(tutorial));
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

    private Long getresourceIdFromSearchHit(SearchHit<ResourceEs> hit) {
        return hit.getContent().getResourceId();
    }

    public static class ResourceType {
        public static final short Lesson = 1;
        public static final short Book = 2;
        public static final short Project = 3;
        public static final short Tutorial = 4;
        public static final short Video = 5;
    }
}
