package edu.whu.learneur.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.resource.entity.Project;
import edu.whu.learneur.resource.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @GetMapping("")
    @CrossOrigin
    public ResponseEntity<IPage<Project>> findAllProjects(@RequestParam(defaultValue = "0") Integer pageNum,
                                                          @RequestParam(defaultValue = "20") Integer pageSize) {

        IPage<Project> res = projectService.findAllProjects(pageNum, pageSize);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{projectId}")
    @CrossOrigin
    public ResponseEntity<Project> getProjectById(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.findById(projectId));
    }


}
