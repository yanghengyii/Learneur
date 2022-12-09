package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.resource.dao.BookDao;
import edu.whu.learneur.resource.entity.Book;
import edu.whu.learneur.resource.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {

    public List<Book> addBooks(List<Book> bookList) throws UserServiceException{
        List<Book> success = new ArrayList<>();
        for(Book book : bookList){
            LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Book::getTitle,book.getTitle()).eq(Book::getAuthor, book.getAuthor())
                    .eq(Book::getFileType, book.getFileType()).eq(Book::getDownloadUrl, book.getDownloadUrl());
            List<Book> books = getBaseMapper().selectList(lqw);
            if(books.isEmpty()){
                getBaseMapper().insert(book);
                success.add(book);
            }
            else{
                for(Book b: books) {

                }
            }
        }
        return success;
    }

    public IPage<Book> findBookPage(Long knowledgeId, Integer pageNum, Integer pageSize){
        return getBaseMapper().findBooksByKnowledgeId(knowledgeId, new Page<Book>(pageNum, pageSize));
    }

    @Override
    public IPage<Book> findAllBooks(Integer pageNum, Integer pageSize) {
        Page<Book> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        return getBaseMapper().selectPage(page, lqw);
    }

    public Book findById(long id){
        return getBaseMapper().selectById(id);
    }
}
