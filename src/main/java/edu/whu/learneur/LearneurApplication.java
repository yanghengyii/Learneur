package edu.whu.learneur;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Books;
import edu.whu.learneur.service.IResourcesService;
import edu.whu.learneur.service.impl.ResourcesServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearneurApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearneurApplication.class, args);
    }

}
