package edu.whu.learneur.neo4j.service.impl;

import edu.whu.learneur.neo4j.domain.Relation;
import edu.whu.learneur.neo4j.domain.Knowledge;
import edu.whu.learneur.neo4j.dto.KnowledgeAndRelations;
import edu.whu.learneur.neo4j.service.GKnowledgeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class GKnowledgeServiceImplTest {

    @Autowired
    GKnowledgeService gKnowledgeService;

    @Test
    void getTagById() {

    }

    @Test
    void getTagByName() {

    }

    @Test
    void addTag() {
        Knowledge c = new Knowledge("c","c");

        Knowledge java = new Knowledge("java","java");
        Knowledge dataStructure = new Knowledge("dataStructure","dataStructure");
        Knowledge sysProgram = new Knowledge("sysProgram","sysProgram");
        Knowledge oop = new Knowledge("oop","oop");

        Relation common = new Relation("common","common",java);
        Relation pre = new Relation("pre","pre",dataStructure);
        Relation include = new Relation("include","include",oop);
        Relation associated = new Relation("associated","associated",sysProgram);

        c.setCommons(
                new ArrayList<Relation>(){{
                    add(common);
                }}
        );
        c.setPreKnowledges(
                new ArrayList<Relation>(){{
                    add(pre);
                }}
        );
        c.setIncludes(
                new ArrayList<Relation>(){{
                    add(include);
                }}
        );
        c.setAssociateds(
                new ArrayList<Relation>(){{
                    add(associated);
                }}
        );

        gKnowledgeService.addTag(c);
    }

    @Test
    void testGetTagByName() {
        List<Knowledge> c = gKnowledgeService.getTagByName("c");
        System.out.println(c);
    }

    @Test
    void getGraphByName() {
        KnowledgeAndRelations c = gKnowledgeService.getGraphByName("c");
        System.out.println(c);
    }

    @Test
    void updateTagById() {
        Map[] o = gKnowledgeService.updateTagById(new Knowledge("ccc","ccc"),5L);
        System.out.println(o);
    }

    @Test
    void addRelation() {
        Map[] o = gKnowledgeService.addRelation(5L,6L,"include","include");
        System.out.println(o);
    }

    @Test
    void updateRelationById() {
        Map[] o = gKnowledgeService.updateRelationById(3L,"include","includesss");
        System.out.println(o);
    }

    @Test
    void deleteTagById() {
        Map[] o = gKnowledgeService.deleteTagById(1L);
        System.out.println(o);
    }

    @Test
    void deleteRelationById() {
        Map[] o = gKnowledgeService.deleteRelationById(0L);
        System.out.println(o);
    }
}