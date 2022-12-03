package edu.whu.learneur.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 点赞
 * </p>
 *
 * @author Learneur
 * @since 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("thumbups")
public class ThumbUps implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 点赞主键
     */
    @TableId(value = "thumb_up_id", type = IdType.AUTO)
    Long idThumbUp;

    /**
     * 用户id
     */
    @TableField("user_id")
    Long idUser;

    /**
     * 笔记id
     */
    @TableField("note_id")
    Long idNote;

}
