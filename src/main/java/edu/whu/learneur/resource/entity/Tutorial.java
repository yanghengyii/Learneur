package edu.whu.learneur.resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {
    @TableId(value = "id_tutorial",type = IdType.AUTO)
    private Long id;

    String name;

    String link;

    String summary;

    @TableField(exist = false)
    List<Knowledge> knowledge;

}
