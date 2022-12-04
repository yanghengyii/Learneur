package edu.whu.learneur;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Books;
import edu.whu.learneur.service.IBooksService;
import edu.whu.learneur.service.IResourcesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearneurApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private IBooksService booksService;

    @Autowired
    private IResourcesService resourcesService;

    @Test
    void test() {
        IPage<Books> resources = resourcesService.findResources(Books.class, 1, 1);
        System.out.println(resources.getRecords());
    }

}
