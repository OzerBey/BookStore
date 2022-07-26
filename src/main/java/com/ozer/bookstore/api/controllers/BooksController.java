package com.ozer.bookstore.api.controllers;

import com.ozer.bookstore.business.abstracts.BookService;
import com.ozer.bookstore.core.utilities.results.DataResult;
import com.ozer.bookstore.core.utilities.results.Result;
import com.ozer.bookstore.core.utilities.results.SuccessDataResult;
import com.ozer.bookstore.entities.concretes.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping("add")
    public Result add(@RequestBody Book book) {
        return this.bookService.add(book);
    }

    @PutMapping("update")
    public Result update(@RequestBody Book book) {
        return this.bookService.update(book);
    }


    @DeleteMapping("delete")
    public Result delete(@RequestParam("id") int id) {
        return this.bookService.deleteById(id);
    }


    @GetMapping("getById")
    public DataResult<Optional<Book>> getBookById(@RequestParam(name = "id") int id) {
        return this.bookService.getById(id);
    }

    @GetMapping("getAll")
    public DataResult<List<Book>> getAll() {
        return this.bookService.getAll();
    }
}
