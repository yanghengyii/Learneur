package edu.whu.learneur.neo4j.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;


/**
 * <p>
 *     知识图谱数据库节点关系实体类
 *    用于存储知识图谱数据库中的关系
 * </p>
 * @author Geraltigas
 * @since 2022-12-10
 * @version 1.0
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@RelationshipProperties
public class Relation {

    /**
     * <p>
     *     关系id
     * </p>
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * <p>
     *     关系类型
     * </p>
     */
    @Property("type")
    private String type;

    /**
     * <p>
     *     关系描述
     * </p>
     */
    @Property("description")
    private String description;

    /**
     * <p>
     *     关系目标节点
     * </p>
     */
    @TargetNode
    @JsonIgnore
    private Knowledge knowledge;


    /**
     * <p>
     *     关系起始节点id
     * </p>
     */
    private Long start;


    /**
     * <p>
     *     关系目标节点id
     * </p>
     */
    private Long end;

}
