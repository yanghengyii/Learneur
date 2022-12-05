package edu.whu.learneur.resource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.learneur.resource.entity.Tutorial;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TutorialDao extends BaseMapper<Tutorial> {
}
