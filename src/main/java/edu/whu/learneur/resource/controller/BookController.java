package edu.whu.learneur.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.learneur.resource.entity.Book;
import edu.whu.learneur.resource.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping("")
    @CrossOrigin
    public ResponseEntity<IPage<Book>> findAllBooks(@RequestParam(defaultValue = "0") Integer pageNum,
                                                    @RequestParam(defaultValue = "20") Integer pageSize) {
        IPage<Book> res = bookService.findAllBooks(pageNum, pageSize);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Book> findAllBooks(@PathVariable Long id) {
        Book res = bookService.findById(id);
        return ResponseEntity.ok(res);
    }

}
