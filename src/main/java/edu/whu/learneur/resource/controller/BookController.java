package edu.whu.learneur.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.resource.entity.Book;
import edu.whu.learneur.resource.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping("")
    public ResponseEntity<IPage<Book>> findAllBooks(@RequestParam Integer pageNum,
                                                    @RequestParam Integer pageSize) {
        IPage<Book> res = bookService.findAllBooks(pageNum, pageSize);
        return ResponseEntity.ok(res);
    }

}
