package com.ozer.bookstore.core.utilities.results;

import com.ozer.bookstore.entities.concretes.Book;

import java.util.Optional;

public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult(T data, String message) {
        super(data, true, message);

    }

    public SuccessDataResult(SuccessDataResult<Optional<Book>> data) {
        super((T) data, true);
    }

    public SuccessDataResult(SuccessDataResult<Optional<Book>> data, String message) {
        super((T) data, true, message);
    }

    public SuccessDataResult(String message) {
        super(null, true, message);

    }

    public SuccessDataResult() {
        super(null, true);

    }

}
