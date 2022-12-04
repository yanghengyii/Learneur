package edu.whu.learneur.dao;

import edu.whu.learneur.domain.Projects;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 项目 Mapper 接口
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Mapper
public interface ProjectsDao extends BaseMapper<Projects> {
}
