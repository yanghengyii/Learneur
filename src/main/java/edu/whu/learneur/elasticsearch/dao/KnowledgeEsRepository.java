package edu.whu.learneur.elasticsearch.dao;

import edu.whu.learneur.elasticsearch.entity.KnowledgeEs;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface KnowledgeEsRepository extends ElasticsearchRepository<KnowledgeEs, Long> {

    @Query("${elasticsearch.query.match-knowledge}")
    SearchPage<KnowledgeEs> matchKnowledge(String keyword);
}
