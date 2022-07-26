package com.ozer.bookstore.api.controllers;

import com.ozer.bookstore.business.abstracts.AuthorService;
import com.ozer.bookstore.core.utilities.exceptions.AuthorNotFoundException;
import com.ozer.bookstore.core.utilities.results.DataResult;
import com.ozer.bookstore.core.utilities.results.Result;
import com.ozer.bookstore.entities.concretes.Author;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/authors")
public class AuthorsController {

    private AuthorService authorService;

    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @PostMapping("add")
    public Result add(@RequestBody Author author) {
        return this.authorService.add(author);
    }


    @PutMapping("update")
    public Result update(@RequestBody Author author) throws AuthorNotFoundException {
        return this.authorService.update(author);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestParam("id") int id) throws AuthorNotFoundException {
        return this.authorService.deleteById(id);
    }

    @GetMapping("getById")
    public DataResult<Optional<Author>> getById(@RequestParam(name = "id") int authorId) {
        return this.authorService.getById(authorId);
    }

    @GetMapping("getAll")
    public DataResult<List<Author>> getAll() {
        return this.authorService.getAll();
    }
}
