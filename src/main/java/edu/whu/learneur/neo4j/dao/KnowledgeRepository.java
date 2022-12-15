package edu.whu.learneur.neo4j.dao;

import edu.whu.learneur.neo4j.domain.Knowledge;
import edu.whu.learneur.neo4j.domain.Relation;
import org.neo4j.driver.Record;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *     知识图谱数据库访问接口
 *     继承Neo4jRepository接口
 *     用于访问数据库
 * </p>
 * @author Geraltigas
 * @since 2022-12-10
 * @version 1.0
 */
@Repository
public interface KnowledgeRepository extends Neo4jRepository<Knowledge,Long> {

    /**
     * <p>
     *     根据节点name查找节点
     * </p>
     * @return 更新后的关系
     */
    @Query("MATCH (n:knowledge) WHERE n.name = $name RETURN n")
    Optional<List<Knowledge>> findByName(String name);

    /**
     * <p>
     *     查找随机25个节点
     * </p>
     * @return 节点列表
     */
    @Query("MATCH (n:knowledge) RETURN n LIMIT 25")
    Optional<List<Knowledge>> findFirst25Knowledge();

}
