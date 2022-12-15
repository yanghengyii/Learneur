package edu.whu.learneur.elasticsearch;

import edu.whu.learneur.elasticsearch.dao.KnowledgeEsRepository;
import edu.whu.learneur.elasticsearch.entity.KnowledgeEs;
import edu.whu.learneur.elasticsearch.entity.ResourceEs;
import edu.whu.learneur.elasticsearch.service.KnowledgeSearchService;
import edu.whu.learneur.elasticsearch.service.ResourceSearchService;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import lombok.var;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.UncategorizedElasticsearchException;
import org.springframework.data.elasticsearch.annotations.Query;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class KnowledgeSearchServiceTest {
    @Resource
    private KnowledgeEsRepository knowledgeEsRepository;
    @Resource
    private KnowledgeSearchService knowledgeSearchService;
    @Autowired
    private ElasticsearchClient elasticsearchClient;


    @Value("${elasticsearch.query.match-knowledge}")
    private String dslMatchKnowledge;

    @Value("${elasticsearch.script.update.add-knowledge-resources}")
    private String dslAddKnowledgeResource;

    @Test
    void daoSaveTest() {
        System.out.println(dslAddKnowledgeResource);
        init();
        Assertions.assertEquals(knowledgeEsRepository.count(), 1L);
        var knowledgeEs = knowledgeEsRepository.findById(1L);
        Assertions.assertTrue(knowledgeEs.isPresent());
        Assertions.assertEquals(knowledgeEs.get().getResources().size(), 5);
    }

    @Test
    void getTest() {
        System.out.println(knowledgeEsRepository.findById(1L));
    }

    @Test
    void daoAddTest() {
        init();
        ResourceEs resourceEs5 = ResourceEs.builder()
                .resourceType(ResourceSearchService.ResourceType.Book)
                .resourceId(5L)
                .name("name5_changed")
                .description("description5_changed")
                .build();
        knowledgeEsRepository.addKnowledgeResource(1L, resourceEs5);
        Assertions.assertEquals(knowledgeEsRepository.count(), 1L);
        var knowledgeEs = knowledgeEsRepository.findById(1L);
        Assertions.assertTrue(knowledgeEs.isPresent());
        Assertions.assertEquals(knowledgeEs.get().getResources().get(4).getName(), "name5_changed");
    }

    @Test
    void searchTest() throws NoSuchMethodException {
        System.out.println(dslMatchKnowledge);
        init();
        try {
            // knowledgeSearchService.search("name1", PageRequest.of(0, 10)).forEach(System.out::println);
            var a = knowledgeEsRepository.matchKnowledge("name1", PageRequest.of(0, 10));
            if (a != null ) a.forEach(System.out::println);
            else System.out.println("null");
        } catch (UncategorizedElasticsearchException e) {
            Assertions.fail(e.getResponseBody());
        }
    }

    @Test
    void daoDeleteBatchTest() {
        init();
        ResourceEs resourceEs1 = ResourceEs.builder()
                .resourceType(ResourceSearchService.ResourceType.Book)
                .resourceId(1L)
                .build();
        ResourceEs resourceEs2 = ResourceEs.builder()
                .resourceType(ResourceSearchService.ResourceType.Lesson)
                .resourceId(1L)
                .build();
        List<ResourceEs> resourceEsList = new ArrayList<>();
        resourceEsList.add(resourceEs1);
        resourceEsList.add(resourceEs2);
        knowledgeEsRepository.removeKnowledgeResources(1L, resourceEsList);

        Assertions.assertEquals(knowledgeEsRepository.count(), 1L);
        var knowledgeEs = knowledgeEsRepository.findById(1L);
        Assertions.assertTrue(knowledgeEs.isPresent());
        Assertions.assertEquals(knowledgeEs.get().getResources().size(), 3);

    }

    @Test
    void daoDeleteTest() {
        init();
        ResourceEs resourceEs1 = ResourceEs.builder()
                .resourceType(ResourceSearchService.ResourceType.Book)
                .resourceId(1L)
                .build();
        knowledgeEsRepository.removeKnowledgeResource(1L, resourceEs1);

        Assertions.assertEquals(knowledgeEsRepository.count(), 1L);
        var knowledgeEs = knowledgeEsRepository.findById(1L);
        Assertions.assertTrue(knowledgeEs.isPresent());
        Assertions.assertEquals(knowledgeEs.get().getResources().size(), 4);

    }

    @Test
    void DaoUpdateTest() {
        init();
        ResourceEs resourceEs1 = ResourceEs.builder()
                .resourceType(ResourceSearchService.ResourceType.Book)
                .resourceId(1L)
                .name("name1_changed")
                .description("description1_changed")
                .build();
        knowledgeEsRepository.updateResource(resourceEs1);

        Assertions.assertEquals(knowledgeEsRepository.count(), 1L);
        var knowledgeEs = knowledgeEsRepository.findById(1L);
        Assertions.assertTrue(knowledgeEs.isPresent());
        for ( var r: knowledgeEs.get().getResources()) {
            if (r.getResourceId() == 1L && r.getResourceType() == ResourceSearchService.ResourceType.Book) {
                Assertions.assertEquals(r.getName(), "name1_changed");
                Assertions.assertEquals(r.getDescription(), "description1_changed");
            }
        }

        resourceEs1.setResourceId(100L);
        resourceEs1.setDescription("dlasjdalsdjka");
        knowledgeEsRepository.updateResource(resourceEs1);

        Assertions.assertEquals(knowledgeEsRepository.count(), 1L);
        knowledgeEs = knowledgeEsRepository.findById(1L);
        Assertions.assertTrue(knowledgeEs.isPresent());
        for ( var r: knowledgeEs.get().getResources()) {
            Assertions.assertNotEquals("dlasjdalsdjka", r.getDescription());
        }
    }

    @Test
    void daoDeleteResourceTest() {
        init();
        ResourceEs resourceEs1 = ResourceEs.builder()
                .resourceType(ResourceSearchService.ResourceType.Book)
                .resourceId(1L)
                .build();
        knowledgeEsRepository.deleteResource(resourceEs1);

        Assertions.assertEquals(knowledgeEsRepository.count(), 1L);
        var knowledgeEs = knowledgeEsRepository.findById(1L);
        Assertions.assertTrue(knowledgeEs.isPresent());
        Assertions.assertEquals(knowledgeEs.get().getResources().size(), 4);
        for ( var r: knowledgeEs.get().getResources()) {
            Assertions.assertNotEquals("name1", r.getName());
        }

    }
    private void init() {
        clear();
        knowledgeEsRepository.save(new KnowledgeEs(1L, "title", "content"));
        ResourceEs resourceEs1 = ResourceEs.builder()
                .resourceType(ResourceSearchService.ResourceType.Book)
                .resourceId(1L)
                .name("name1")
                .description("description1")
                .build();
        ResourceEs resourceEs2 = ResourceEs.builder()
                .resourceType(ResourceSearchService.ResourceType.Lesson)
                .resourceId(1L)
                .name("name2")
                .description("description2")
                .build();
        ResourceEs resourceEs3 = ResourceEs.builder()
                .resourceType(ResourceSearchService.ResourceType.Video)
                .resourceId(2L)
                .name("name3")
                .description("description3")
                .build();
        ResourceEs resourceEs4 = ResourceEs.builder()
                .resourceType(ResourceSearchService.ResourceType.Book)
                .resourceId(4L)
                .name("name4")
                .description("description4")
                .build();
        ResourceEs resourceEs5 = ResourceEs.builder()
                .resourceType(ResourceSearchService.ResourceType.Book)
                .resourceId(5L)
                .name("name5")
                .description("description5")
                .build();
        List<ResourceEs> resourceEsList = new ArrayList<ResourceEs>() {{
            add(resourceEs1);
            add(resourceEs2);
            add(resourceEs3);
            add(resourceEs4);
            add(resourceEs5);
        }};

        knowledgeEsRepository.addknowledgeResources(1L, resourceEsList);
    }


    private void clear() {
        knowledgeEsRepository.deleteAll();
    }
}
