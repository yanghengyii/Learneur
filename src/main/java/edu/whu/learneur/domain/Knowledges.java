package edu.whu.learneur.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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


}
