package edu.whu.learneur.resource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.resource.entity.Book;
import edu.whu.learneur.resource.entity.Project;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BookDao extends BaseMapper<Book> {
    @Select("SELECT id_book,title,img_path,path,author,publisher,language,download_url FROM knowledge_resource natural join book where id_knowledge = ${KnowledgeId}")
    IPage<Book> findBooksByKnowledgeId(Long KnowledgeId, Page<Book> page);

    @Select("SELECT * FROM book")
    @Results({@Result(id = true, property = "id", column = "id_book"),
            @Result(property = "knowledge", column = "id_resource",
                    many = @Many(
                            select = "edu.whu.learneur.resource.dao.KnowledgeDao.findKnowledgeByBookId"
                    ))
    })
    IPage<Book> findBooks(Page<Book> page);
}
