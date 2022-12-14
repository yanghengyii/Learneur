package edu.whu.learneur.resource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.resource.entity.Book;
import edu.whu.learneur.resource.entity.Project;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BookDao extends BaseMapper<Book> {
    @Select("SELECT book.* FROM knowledge_resource join book on knowledge_resource.id_resource = book.id_book where id_knowledge = #{KnowledgeId} and type = 2")
    IPage<Book> findBooksByKnowledgeId(Long KnowledgeId, Page<Book> page);

    @Select("SELECT * FROM book")
    @Results({@Result(id = true, property = "idBook", column = "id_book"),
            @Result(property = "knowledge", column = "id_book",
                    many = @Many(
                            select = "edu.whu.learneur.resource.dao.KnowledgeDao.findKnowledgeByBookId"
                    ))
    })
    IPage<Book> findBooks(Page<Book> page);

    @Insert("INSERT INTO knowledge_resource VALUES(#{bookId},#{knowledgeId}, 2)")
    void insertKR(long bookId,long knowledgeId);

    @Select("select exists (select * from knowledge_resource where id_knowledge = #{knowledgeId} "
            + "and id_resource = #{bookId} and type = 2)")
    int existKR(long knowledgeId, long bookId);

    @Select("SELECT * FROM book where id_book=#{id}")
    @Results({@Result(id = true, property = "idBook", column = "id_book"),
            @Result(property = "knowledge", column = "id_book",
                    many = @Many(
                            select = "edu.whu.learneur.resource.dao.KnowledgeDao.findKnowledgeByBookId"
                    ))
    })
    Book findBookById(long id);
}
