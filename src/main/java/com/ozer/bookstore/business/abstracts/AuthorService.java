package com.ozer.bookstore.business.abstracts;

import com.ozer.bookstore.core.utilities.exceptions.AuthorNotFoundException;
import com.ozer.bookstore.core.utilities.results.DataResult;
import com.ozer.bookstore.core.utilities.results.Result;
import com.ozer.bookstore.core.utilities.results.SuccessDataResult;
import com.ozer.bookstore.entities.concretes.Author;
import com.ozer.bookstore.entities.concretes.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Result add(Author author);

    Result deleteById(int id) throws AuthorNotFoundException;

    Result update(Author author) throws AuthorNotFoundException;

    DataResult<Optional<Author>> getById(int authorId);

    DataResult<List<Author>> getAll();
}
