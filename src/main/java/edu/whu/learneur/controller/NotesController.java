package edu.whu.learneur.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.domain.Notes;
import edu.whu.learneur.domain.Users;
import edu.whu.learneur.elasticsearch.service.NoteSearchService;
import edu.whu.learneur.exception.NotesServiceException;
import edu.whu.learneur.service.INotesService;
import edu.whu.learneur.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 学习笔记 前端控制器
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@RestController
@RequestMapping("/notes")
@CrossOrigin
public class NotesController {
    @Autowired
    private INotesService notesService;


    @Autowired
    private IUsersService usersService;

    @Autowired
    private NoteSearchService noteSearchService;

    @GetMapping("/detail/{idNote}")
    @PermitAll
    @CrossOrigin
    public ResponseEntity<Notes> getNoteDetail(@PathVariable Long idNote) {
        return ResponseEntity.ok(notesService.findNotes(idNote));
    }

    @GetMapping("/get-user")
    @PermitAll
    @CrossOrigin
    public ResponseEntity<IPage<Notes>> getUserNotes(@RequestParam long id){
        return ResponseEntity.ok(notesService.findNotesByUser(id,0,1000));
    }

    @GetMapping("/get-notes-by-time")
    @PermitAll
    @CrossOrigin
    public ResponseEntity<IPage<Notes>> getNotesByTime(
            @RequestParam(value = "pages", defaultValue = "0") int pages,
            @RequestParam(value = "cols", defaultValue = "15") int cols
    ) {
        return ResponseEntity.ok(notesService.findNotesOrderByCreateTime(pages, cols));
    }

    @DeleteMapping("/delete/{idNote}")
    @PreAuthorize("hasAnyAuthority('admin') or #username = authentication.name")
    public ResponseEntity<Boolean> deleteNote(
            @RequestParam(value = "username", defaultValue = "") String username, // 前端传输当前操作用户用户名, 作者才拥有删除权力
            @PathVariable Long idNote
    ) {
        notesService.incrementViewCount(idNote);
        noteSearchService.delete(idNote);
        return ResponseEntity.ok(notesService.deleteNote(idNote));
    }

    @DeleteMapping("/delete/{idNotes}")
    @PreAuthorize("hasAnyAuthority('admin') or #username = authentication.name")
    public ResponseEntity<Boolean> deleteNoteByBatch(
            @RequestParam(value = "username", defaultValue = "") String username,
            @PathVariable List<Long> idNotes
    ) {
        noteSearchService.delete(idNotes);
        return ResponseEntity.ok(notesService.deleteNoteByBatch(idNotes));
    }

    @PostMapping("/post")
    //@PreAuthorize("hasAnyAuthority('user','admin') or #username = authentication.name")
    @CrossOrigin
    public ResponseEntity<Notes> postNote(
            @RequestBody Notes note
    ) {
        Notes notes = notesService.postNote(note, note.getNoteAuthorId(), note.getIdResources());
        if(Objects.isNull(notes)) {
            return ResponseEntity.badRequest().build();
        }
        noteSearchService.save(notes);
        return ResponseEntity.ok(note);
    }

    @PutMapping("/update/{idNote}")
    @PreAuthorize("hasAnyAuthority('admin') or #username = authentication.name")
    public ResponseEntity<Notes> updateNote (
            @RequestBody Notes note,
            @RequestParam(value = "username", defaultValue = "") String username,
            @PathVariable Long idNote
    ) {
        note.setNoteId(idNote);
        noteSearchService.save(note);
        return ResponseEntity.ok(notesService.updateNote(idNote, note));
    }

    @GetMapping("/check-thumb-up")
    @PermitAll
    public ResponseEntity<Boolean> checkThumbUp(
            @RequestParam(value = "idUser", defaultValue = "-1") Long idUser,
            @RequestParam(value = "idNote", defaultValue = "-1") Long idNote
    ) {
        if(idUser == -1 || idNote == -1) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(notesService.checkThumbUp(idNote, idUser));
    }

    @PatchMapping("/thumb-up")
    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    public ResponseEntity<Boolean> thumbUp(
            @RequestParam(value = "idNote", defaultValue = "-1") Long idNote,
            @RequestParam(value = "idUser", defaultValue = "-1") Long idUser
    ) {
        if(idUser == -1 || idNote == -1) {
            return ResponseEntity.badRequest().build();
        }
        if (notesService.thumbUp(idNote, idUser)) {
            throw new NotesServiceException("点赞操作错误!");
        }
        return ResponseEntity.ok(notesService.checkThumbUp(idNote, idUser));
    }

}

