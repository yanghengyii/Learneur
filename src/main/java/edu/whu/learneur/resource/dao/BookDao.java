package edu.whu.learneur.resource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.resource.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookDao extends BaseMapper<Book> {
    @Select("SELECT id_book,title,img_path,path,author,publisher,language,download_url FROM knowledge_resource natural join book where id_knowledge = ${KnowledgeId}")
    IPage<Book> findBooksByKnowledgeId(Long KnowledgeId, Page<Book> page);
}
