package edu.whu.learneur.neo4j.dao;

import edu.whu.learneur.neo4j.domain.Knowledge;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KnowledgeRepoTest {

    @Autowired
    KnowledgeRepo knowledgeRepo;

    @Test
    void updateRelationById() {
        knowledgeRepo.updateRelationById(9L,"tasdest","tasdsaest");
    }

    @Test
    void updateTagById() {
        knowledgeRepo.updateTagById(new Knowledge("ok1212","ok1121"),1L);
    }

    @Test
    void deleteById() {
        knowledgeRepo.deleteById(1L);
    }

    @Test
    void deleteRelationById() {
        knowledgeRepo.deleteRelationById(11L);
    }

    @Test
    void findRelationByNameAndType() {
        knowledgeRepo.findRelationByNameAndType("c","common");
    }
}