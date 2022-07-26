package com.ozer.bookstore.test;

import com.ozer.bookstore.core.utilities.exceptions.AuthorNotFoundException;
import com.ozer.bookstore.core.utilities.exceptions.UserNotFoundException;
import com.ozer.bookstore.core.utilities.results.DataResult;
import com.ozer.bookstore.entities.concretes.Book;

import java.util.Optional;

public interface TestCrud {

    // Test for book's CRUD operations //
    void testBookCreate();

    void testBookUpdate();


    DataResult<Optional<Book>> testBookGetById();

    void testBookList();

    void testBookDelete();


    // Tests for author's CRUD operations //

    void testAuthorCreate();

    void testAuthorUpdate();

    DataResult<Optional<Book>> testAuthorGetById();

    void testAuthorList();

    void testAuthorDelete() throws AuthorNotFoundException;


    // Tests for user's CRUD operations //

    void testUserCreate();

    void testUserUpdate();

    DataResult<Optional<Book>> testUserGetById();

    void testUserList();

    void testUserDelete() throws UserNotFoundException;
}
