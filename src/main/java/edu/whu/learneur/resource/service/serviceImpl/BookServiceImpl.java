package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.exception.ResourceException;
import edu.whu.learneur.resource.dao.BookDao;
import edu.whu.learneur.resource.entity.Book;
import edu.whu.learneur.resource.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {

    public List<Book> addBooks(List<Book> bookList, long knowledgeId){
        List<Book> success = new ArrayList<>();
        for(Book book : bookList){
            LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Book::getTitle,book.getTitle()).eq(Book::getAuthor, book.getAuthor())
                    .eq(Book::getFileType, book.getFileType());
            Book tmp = getBaseMapper().selectOne(lqw);
            if(tmp == null){
                try{
                    getBaseMapper().insert(book);
                    success.add(book);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
            else if(!tmp.equals(book)){
                book.setId(tmp.getId());
                getBaseMapper().updateById(book);
            }
            if(getBaseMapper().existKR(knowledgeId, book.getId()) == 0){
                getBaseMapper().insertKR(book.getId(),knowledgeId);
            }
        }
        return success;
    }

    public IPage<Book> findBookPage(Long knowledgeId, Integer pageNum, Integer pageSize){
        return getBaseMapper().findBooksByKnowledgeId(knowledgeId, new Page<>(pageNum, pageSize));
    }

    @Override
    public IPage<Book> findAllBooks(Integer pageNum, Integer pageSize) {
        Page<Book> page = new Page<>(pageNum, pageSize);
        return getBaseMapper().findBooks(page);
    }

    public Book findById(long id){
        return getBaseMapper().selectById(id);
    }
}
