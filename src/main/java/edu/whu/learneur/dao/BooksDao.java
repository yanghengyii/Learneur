package edu.whu.learneur.dao;

import edu.whu.learneur.domain.Books;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 书籍 Mapper 接口
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Mapper
public interface BooksDao extends BaseMapper<Books> {

}
