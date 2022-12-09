package edu.whu.learneur.elasticsearch.dao;

import edu.whu.learneur.elasticsearch.entity.NoteEs;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteEsRepository extends ElasticsearchRepository<NoteEs, Long> {

    SearchPage<NoteEs> findByTitleOrContent(String title, String content, Pageable pageable);
}
