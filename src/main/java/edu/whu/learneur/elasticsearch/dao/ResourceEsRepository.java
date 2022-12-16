package edu.whu.learneur.elasticsearch.dao;

import edu.whu.learneur.elasticsearch.entity.ResourceEs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceEsRepository extends ElasticsearchRepository<ResourceEs, String> {

    @Query("{\n" +
            "        \"bool\": {\n" +
            "          \"filter\": { \"term\": { \"resourceType\": \"?0\" } },\n" +
            "          \"must\" : [{\n" +
            "            \"bool\": {\n" +
            "              \"should\": [\n" +
            "                          { \"term\": { \"name.keyword\": { \"value\": \"?1\", \"boost\": 100 } } },\n" +
            "                          { \"match\": { \"name\": \"?1\" } },\n" +
            "                          { \"match\": { \"description\": \"?1\" } }\n" +
            "                        ]\n" +
            "            }\n" +
            "          }]\n" +
            "        }\n" +
            "      }")
    SearchPage<ResourceEs> matchSpecificTypeResource(int type, String keyword, Pageable pageable);

    @Query("{\n" +
            "        \"bool\": {\n" +
            "          \"should\": [\n" +
            "            { \"term\": { \"name.keyword\": { \"value\": \"?0\", \"boost\": 100 } } },\n" +
            "            { \"match\": { \"name\": \"?0\" } },\n" +
            "            { \"match\": { \"description\": \"?0\" } }\n" +
            "          ]\n" +
            "        }\n" +
            "      }")
    SearchPage<ResourceEs> matchAllTypeResource(String keyword, Pageable pageable);

    ResourceEs findByResourceTypeAndResourceId(Short resourceType, Long resourceId);

    void deleteByResourceTypeAndResourceId(Short resourceType, Long resourceId);
}
