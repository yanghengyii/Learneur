package edu.whu.learneur.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;
import java.util.List;

@Document(indexName = "learneur_knowledge")
@Setting(replicas = 0)
@Data
public class KnowledgeEs implements Serializable {
    @Id
    private Long id;

    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word"), otherFields = {
            @InnerField(suffix = "keyword", type = FieldType.Keyword)
    })
    private String name;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String description;

    @Field(type = FieldType.Nested)
    private List<ResourceEs> resources;

    public KnowledgeEs(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
