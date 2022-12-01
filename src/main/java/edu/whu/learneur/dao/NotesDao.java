package edu.whu.learneur.dao;

import edu.whu.learneur.domain.Notes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 学习笔记 Mapper 接口
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Mapper
public interface NotesDao extends BaseMapper<Notes> {

}
