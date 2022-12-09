package edu.whu.learneur.elasticsearch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ResourceSearchServiceTest {

    @Value("${elasticsearch.query.match-specific-type-resource}")
    private String dslMatchSpecificTypeResource;

    @Test
    void dslStringCheck() {
        System.out.println(dslMatchSpecificTypeResource);
    }
}
