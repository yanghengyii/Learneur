package edu.whu.learneur.elasticsearch.service;

import edu.whu.learneur.domain.Notes;
import edu.whu.learneur.elasticsearch.dao.NoteEsRepository;
import edu.whu.learneur.elasticsearch.entity.NoteEs;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Setter(onMethod_ = @Autowired)
public class NoteSearchService {

    private NoteEsRepository noteEsRepository;

    public Page<Long> search(String keyword, Pageable pageable) {
        return noteEsRepository.matchNote(keyword, pageable)
                .map(noteEsSearchHit -> noteEsSearchHit.getContent().getId());
    }

    public void save(Notes note) {
        NoteEs noteEs = new NoteEs(note.getNoteId(), note.getNoteTitle(), note.getNoteContent());
        noteEsRepository.save(noteEs);
    }

    public void delete(Notes note) {
        noteEsRepository.deleteById(note.getNoteId());
    }
}
