package edu.whu.learneur.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("resource")
public class Resources implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_resources", type = IdType.ASSIGN_ID)
    private Long idResources;

    /**
     * 资源类型: 1:网课; 2:书籍; 3:项目
     */
    private Integer type;

    /**
     * 对应资源类型子表下的id
     */
    @TableField(value = "id_specific")
    private Long idSpecific;


}
