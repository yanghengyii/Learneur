package edu.whu.learneur.crawler.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.learneur.crawler.entity.Video;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoDao extends BaseMapper<Video> {
}
