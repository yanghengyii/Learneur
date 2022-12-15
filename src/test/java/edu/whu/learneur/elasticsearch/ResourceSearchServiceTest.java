package edu.whu.learneur.elasticsearch;

import edu.whu.learneur.elasticsearch.dao.KnowledgeEsRepository;
import edu.whu.learneur.elasticsearch.entity.ResourceEs;
import edu.whu.learneur.elasticsearch.service.KnowledgeSearchService;
import edu.whu.learneur.elasticsearch.service.ResourceSearchService;
import edu.whu.learneur.resource.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ResourceSearchServiceTest {

    @Value("${elasticsearch.query.match-specific-type-resource}")
    private String dslMatchSpecificTypeResource;

    @Resource
    private ResourceSearchService resourceSearchService;

    @Test
    void saveTest() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Elasticsearch使用教程一篇就够了");
        resourceSearchService.save(book);
    }
    @Test
    void dslStringCheck() {
        resourceSearchService.search("java教程", 2, 0, 10).getRecords().forEach(System.out::println);
    }
}
