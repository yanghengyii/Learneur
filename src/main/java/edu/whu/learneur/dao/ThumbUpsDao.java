package edu.whu.learneur.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.learneur.domain.ThumbUps;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThumbUpsDao extends BaseMapper<ThumbUps> {
}
