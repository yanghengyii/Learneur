package edu.whu.learneur.crawler.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {
    @TableId(value = "id_tutorial",type = IdType.ASSIGN_ID)
    private Long id;

    String name;

    String link;

    String summary;

}
