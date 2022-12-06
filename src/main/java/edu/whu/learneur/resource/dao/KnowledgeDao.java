package edu.whu.learneur.resource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.learneur.domain.Knowledge;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface KnowledgeDao extends BaseMapper<Knowledge> {
    @Select("select * from knowledge")
    List<Knowledge> findAll();
}
