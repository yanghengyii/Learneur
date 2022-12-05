package edu.whu.learneur.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.Neo4jClient;

@Configuration
public class Beans {
//    @Autowired
//    Neo4jClient neo4jClient;

//    @Bean
//    public Neo4jClient getNeo4jClient() {
////        Driver driver = GraphDatabase
////                .driver("neo4j://localhost:7687", AuthTokens.basic("neo4j", "secret"));
////        Neo4jClient client = Neo4jClient.create(driver);
//        return client;
//    }
}
