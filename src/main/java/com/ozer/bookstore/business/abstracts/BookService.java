package com.ozer.bookstore.business.abstracts;

import com.ozer.bookstore.core.utilities.results.DataResult;
import com.ozer.bookstore.core.utilities.results.Result;
import com.ozer.bookstore.core.utilities.results.SuccessDataResult;
import com.ozer.bookstore.entities.concretes.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Result add(Book book);

    Result deleteById(int id);

    Result update(Book book);

    SuccessDataResult<Optional<Book>> getById(int bookId);

    DataResult<List<Book>> getAll();
}
