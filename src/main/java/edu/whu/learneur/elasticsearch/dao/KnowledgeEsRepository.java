package edu.whu.learneur.elasticsearch.dao;

import edu.whu.learneur.elasticsearch.entity.KnowledgeEs;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeEsRepository extends ElasticsearchRepository<KnowledgeEs, Long>, KnowledgeResourceEsRepository {

    @Query("{\n" +
            "        \"bool\": {\n" +
            "          \"should\": [\n" +
            "            { \"term\": { \"name.keyword\": { \"value\": \"?0\", \"boost\": 100 } } },\n" +
            "            { \"match\": { \"name\": { \"query\": \"?0\", \"boost\": 2.5 } } },\n" +
            "            { \"match\": { \"description\": { \"query\": \"?0\", \"boost\": 2.5 } } },\n" +
            "            {\n" +
            "              \"nested\": {\n" +
            "                \"path\": \"resources\",\n" +
            "                \"query\": {\n" +
            "                  \"bool\": {\n" +
            "                    \"should\": [\n" +
            "                      { \"match\": { \"resources.name\": { \"query\": \"?0\", \"boost\": 1.0 } } },\n" +
            "                      { \"match\": { \"resources.description\": { \"query\": \"?0\", \"boost\": 1.0 } } }\n" +
            "                    ]\n" +
            "                  }\n" +
            "                }\n" +
            "              }\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      }")
    SearchPage<KnowledgeEs> matchKnowledge(String keyword, Pageable pageable);

}
