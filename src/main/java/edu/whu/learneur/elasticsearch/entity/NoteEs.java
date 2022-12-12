package edu.whu.learneur.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;

@Document(indexName = "learneur_note")
@Setting(replicas = 0)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteEs implements Serializable {
    @Id
    private Long id;

    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word"), otherFields = {
            @InnerField(suffix = "keyword", type = FieldType.Keyword)
    })
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;

}
