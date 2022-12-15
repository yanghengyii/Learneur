package edu.whu.learneur.neo4j.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;


/**
 * <p>
 *     知识图谱数据库实体类
 *     用于存储知识图谱数据库中的节点
 * </p>
 * @author Geraltigas
 * @since 2022-12-10
 * @version 1.0
 */
@Data
@Node("knowledge")
@NoArgsConstructor
public class Knowledge {


    /**
     * <p>
     *     节点id
     * </p>
     */
    @Id
    @GeneratedValue
    private Long id;


    /**
     * <p>
     *     节点name
     * </p>
     */
    @Property("name")
    private String name;


    /**
     * <p>
     *     节点描述
     * </p>
     */
    @Property("description")
    private String description;


    /**
     * <p>
     *     节点对应的关系型数据库中的id
     * </p>
     */
    @Property("foreign_id")
    private Long foreignId;

    public Knowledge(String name, String description) {
        this.name = name.toLowerCase();
        this.description = description;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", foreignId=" + foreignId +
                '}';
    }
}
