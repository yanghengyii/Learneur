package edu.whu.learneur.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.constant.ResourcesType;
import edu.whu.learneur.domain.Books;
import edu.whu.learneur.domain.Lessons;
import edu.whu.learneur.domain.Projects;
import edu.whu.learneur.service.IResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 资源 前端控制器
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@RestController
@RequestMapping("/resources")
public class ResourcesController {
    @Autowired
    private IResourcesService resourcesService;

    @GetMapping("/all/books")
    public ResponseEntity<IPage<Books>> findAllBooks(
            @RequestParam(value = "pages", defaultValue = "0") int pages,
            @RequestParam(value = "cols", defaultValue = "15") int cols
    ) {
        return ResponseEntity.ok(resourcesService.findResources(Books.class, pages, cols));
    }

    @GetMapping("/lessons")
    public ResponseEntity<IPage<Lessons>> findAllLessons(
            @RequestParam(value = "pages", defaultValue = "0") int pages,
            @RequestParam(value = "cols", defaultValue = "15") int cols
    ) {
        return ResponseEntity.ok(resourcesService.findResources(Lessons.class, pages, cols));
    }

    @GetMapping("/projects")
    public ResponseEntity<IPage<Projects>> findAllProjects(
            @RequestParam(value = "pages", defaultValue = "0") int pages,
            @RequestParam(value = "cols", defaultValue = "15") int cols
    ) {
        return ResponseEntity.ok(resourcesService.findResources(Projects.class, pages, cols));
    }

    @GetMapping("/books/{idSpecific}")
    public ResponseEntity<Books> findBook(@PathVariable Long idSpecific) {
        return ResponseEntity.ok(resourcesService.findResourceByIdSpecify(idSpecific, Books.class));
    }

    @GetMapping("/lessons/{idSpecific}")
    public ResponseEntity<Lessons> findLesson(@PathVariable Long idSpecific) {
        return ResponseEntity.ok(resourcesService.findResourceByIdSpecify(idSpecific, Lessons.class));
    }

    @GetMapping("/projects/{idSpecific}")
    public ResponseEntity<Projects> findProject(@PathVariable Long idSpecific) {
        return ResponseEntity.ok(resourcesService.findResourceByIdSpecify(idSpecific, Projects.class));
    }
}

