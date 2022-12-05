package edu.whu.learneur.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import edu.whu.learneur.domain.Knowledges;
import edu.whu.learneur.domain.Notes;
import edu.whu.learneur.domain.Users;
import edu.whu.learneur.service.IKnowledgesService;
import edu.whu.learneur.service.INotesService;
import edu.whu.learneur.service.IResourcesService;
import edu.whu.learneur.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IUsersService usersService;

    @Autowired
    private INotesService notesService;

    @Autowired
    private IResourcesService resourcesService;

    @Autowired
    private IKnowledgesService knowledgesService;

    @GetMapping("/users/all")
    public ResponseEntity<IPage<Users>> findAllUsers(
            @RequestParam(value = "pages", defaultValue = "0") int pages,
            @RequestParam(value = "cols", defaultValue = "15") int cols
    ) {
        IPage<Users> users = usersService.findUsers(false, pages, cols);
        if(Objects.isNull(users)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/all-online")
    public ResponseEntity<IPage<Users>> findAllUsersOnline(
            @RequestParam(value = "pages", defaultValue = "0") int pages,
            @RequestParam(value = "cols", defaultValue = "15") int cols
    ) {
        IPage<Users> users = usersService.findUsers(true, pages, cols);
        if(Objects.isNull(users)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users/delete-user-by-batches")
    public ResponseEntity<Boolean> deleteUserByBatches(@RequestBody List<Long> idUsers) {
        return ResponseEntity.ok(usersService.deleteUsersByBatches(idUsers));
    }

    @GetMapping("/notes/all-notes")
    public ResponseEntity<IPage<Notes>> findAllNotes(
            @RequestParam(value = "pages", defaultValue = "0") int pages,
            @RequestParam(value = "cols", defaultValue = "15") int cols
    ) {
        return ResponseEntity.ok(notesService.findNotes(pages, cols));
    }

    @PostMapping("/notes/delete-note-by-batches")
    public ResponseEntity<Boolean> deleteNotesByBatches(@RequestBody List<Long> idNotes) {
        return ResponseEntity.ok(notesService.deleteNoteByBatch(idNotes));
    }

    @PostMapping("/resources/save-or-update")
    public ResponseEntity<Boolean> saveOrUpdateResourcesByBatches(@RequestBody List<Object> resources) {
        return ResponseEntity.ok(resourcesService.saveOrUpdateResources(resources));
    }

    @PostMapping("/resources/delete-resource-by-batches")
    public ResponseEntity<Boolean> deleteResourcesByBatches(@RequestBody List<Long> idResources) {
        return ResponseEntity.ok(resourcesService.deleteResourcesByIds(idResources));
    }

    @PostMapping("/knowledge/save-or-update-knowledge")
    public ResponseEntity<Boolean> saveOrUpdateKnowledge(@RequestBody Knowledges knowledge) {
        return ResponseEntity.ok(knowledgesService.saveOrUpdateKnowledge(knowledge));
    }

    @PostMapping("/knowledge/delete-knowledges")
    public ResponseEntity<Boolean> deleteKnowledges(List<Long> idKnowledges) {
        return ResponseEntity.ok(knowledgesService.deleteKnowledges(idKnowledges));
    }

    @PostMapping("/knowledge/add-knowledge-resource-relation/{idknowledge}")
    public ResponseEntity<Boolean> addKnowledgeResourceRelation(@PathVariable Long idknowledge, @RequestBody List<Long> idResources) {
        return ResponseEntity.ok(knowledgesService.addKnowledgeResourceRelation(idknowledge, idResources));
    }

    @PostMapping("/knowledge/delete-knowledge-resource-relation/{idknowledge}")
    public ResponseEntity<Boolean> deleteKnowledgeResourceRelation(@PathVariable Long idknowledge, @RequestBody List<Long> idResources) {
        return ResponseEntity.ok(knowledgesService.deleteKnowledgeResourceRelation(idknowledge, idResources));
    }
}
