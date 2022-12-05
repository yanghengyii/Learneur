package edu.whu.learneur;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.dao.KnowledgesDao;
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

    @Autowired
    private KnowledgesDao knowledgesDao;

    @Test
    void test() {
        System.out.println(knowledgesDao.selectBooksByKnowledge(1L, new Page<>(1, 2)));
        System.out.println("----------------------------------------------");
        System.out.println(booksService.findBooks(1, 2));
    }

}
