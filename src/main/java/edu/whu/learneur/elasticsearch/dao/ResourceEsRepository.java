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

    @Query("${elasticsearch.query.match-specific-type-resource}")
    SearchPage<ResourceEs> matchSpecificTypeResource(int type, String keyword, Pageable pageable);

    @Query("${elasticsearch.query.match-all-type-resource}")
    SearchPage<ResourceEs> matchAllTypeResource(String keyword, Pageable pageable);

    ResourceEs findByResourceTypeAndResourceId(Short resourceType, Long resourceId);

    void deleteByResourceTypeAndResourceId(Short resourceType, Long resourceId);
}
