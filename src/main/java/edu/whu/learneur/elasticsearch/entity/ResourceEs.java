package edu.whu.learneur.elasticsearch.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;

@Document(indexName = "learneur_resource")
@Setting(replicas = 0)
@Data
@Builder
public class ResourceEs implements Serializable {
    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word"), otherFields = {
            @InnerField(suffix = "keyword", type = FieldType.Keyword)
    })
    private String name;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String description;

    @Field(type = FieldType.Long)
    private Long resourceId;

    @Field(type = FieldType.Short)
    private Short resourceType;

}