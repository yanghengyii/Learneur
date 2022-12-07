package edu.whu.learneur.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.learneur.exception.UserServiceException;
import edu.whu.learneur.resource.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IBookService extends IService<Book> {
    List<Book> addBooks(List<Book> bookList) throws UserServiceException;

    IPage<Book> findBookPage(Long knowledgeId, Integer pageNum, Integer pageSize);

    IPage<Book> findAllBooks(Integer pageNum, Integer pageSize);

    Book findById(long id);
}
