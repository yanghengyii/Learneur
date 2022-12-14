package edu.whu.learneur.elasticsearch.dao;

import edu.whu.learneur.elasticsearch.entity.NoteEs;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteEsRepository extends ElasticsearchRepository<NoteEs, Long> {
    @Query("{\n" +
            "        \"bool\": {\n" +
            "          \"should\": [\n" +
            "            { \"term\": { \"title.keyword\": { \"value\": \"?0\", \"boost\": 100 } } },\n" +
            "            { \"match\": { \"title\": \"?0 } },\n" +
            "            { \"match\": { \"content\": \"?0\" } }\n" +
            "          ]\n" +
            "        }\n" +
            "      }")
    SearchPage<NoteEs> matchNote(String keyword, Pageable pageable);
}
