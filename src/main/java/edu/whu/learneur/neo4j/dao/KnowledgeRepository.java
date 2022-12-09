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

@Repository
public interface KnowledgeRepository extends Neo4jRepository<Knowledge,Long> {
    @Query("MATCH (n:knowledge) WHERE n.name = $name RETURN n")
    Optional<List<Knowledge>> findByName(String name);

    @Query("MATCH (n:knowledge)-[]->(m:knowledge) WHERE n.name = $name RETURN m")
    Optional<List<Knowledge>> findAllRelated(String name);

    @Query("MATCH (n:knowledge) RETURN n LIMIT 25")
    Optional<List<Knowledge>> findFirst25Knowledge();



//    @Query("MATCH (n:knowledge)-[r:preKnowledge]->(m:knowledge) WHERE n.name = $name RETURN r,n.name as from ,m.name as to")
//    Optional<List<Map<String,String>>> findPreKnowledge(String name);
//
//    @Query("MATCH (n:knowledge)-[r:include]->(m:knowledge) WHERE n.name = $name RETURN r,n.name as from ,m.name as to")
//    Optional<List<Map<String,String>>> findIncludes(String name);
//
//    @Query("MATCH (n:knowledge)-[r:common]->(m:knowledge) WHERE n.name = $name RETURN r,n.name as from ,m.name as to")
//    Optional<List<Map<String,String>>> findCommons(String name);
//
//    @Query("MATCH (n:knowledge)-[r:associated]->(m:knowledge) WHERE n.name = $name RETURN r,n.name as from ,m.name as to")
//    Optional<List<Map<String,String>>> findAssociateds(String name);
}
