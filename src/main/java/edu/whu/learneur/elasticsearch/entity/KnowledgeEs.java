package edu.whu.learneur.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = "learneur_knowledge")
@Setting(replicas = 0)
@Data
public class KnowledgeEs {
    @Id
    private Long id;

    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word"), otherFields = {
            @InnerField(suffix = "keyword", type = FieldType.Keyword)
    })
    private String name;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String description;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String resourcesDecription;

}
