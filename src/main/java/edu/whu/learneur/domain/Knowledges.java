package edu.whu.learneur.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 知识点
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("knowledges")
public class Knowledges implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_knowledge", type = IdType.ASSIGN_ID)
    private Long idKnowledge;

    /**
     * 知识点
     */
    @TableField(value = "knowledge_name")
    private String knowledgeName;

    /**
     * 知识点描述
     */
    @TableField(value = "knowledge_description")
    private String knowledgeDescription;

    /**
     * 该知识点下的电子书资源
     */
    @TableField(exist = false)
    List<Books> books;

    /**
     * 该知识点下的网课资源
     */
    @TableField(exist = false)
    List<Lessons> lessons;

    /**
     * 该知识点下的项目资源
     */
    @TableField(exist = false)
    List<Projects> projects;
}
