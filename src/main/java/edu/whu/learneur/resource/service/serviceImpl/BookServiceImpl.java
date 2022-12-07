package edu.whu.learneur.resource.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.learneur.resource.dao.BookDao;
import edu.whu.learneur.resource.entity.Book;
import edu.whu.learneur.resource.entity.Project;
import edu.whu.learneur.resource.service.IBookService;
import edu.whu.learneur.exception.UserServiceException;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {

    public List<Book> addBooks(List<Book> bookList) throws UserServiceException{
        List<Book> success = new ArrayList<>();
        for(Book book : bookList){
            LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Book::getTitle,book.getTitle());
            if(getBaseMapper().selectList(lqw).size()==0){
                getBaseMapper().insert(book);
                success.add(book);
            }
        }
        return success;
    }

    public IPage<Book> findBookPage(Long knowledgeId, Integer pageNum, Integer pageSize){
        return null;
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
