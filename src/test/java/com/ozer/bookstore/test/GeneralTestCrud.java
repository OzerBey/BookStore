package com.ozer.bookstore.test;

import com.ozer.bookstore.core.utilities.exceptions.AuthorNotFoundException;
import com.ozer.bookstore.core.utilities.exceptions.UserNotFoundException;

public interface GeneralTestCrud {

    // Test for book's CRUD operations //
    void testBookCreate();

    void testBookUpdate();


    void testBookGetById();

    void testBookList();

    void testBookDelete();


    // Tests for author's CRUD operations //

    void testAuthorCreate();

    void testAuthorUpdate();

    void testAuthorGetById();

    void testAuthorList();

    void testAuthorDelete() throws AuthorNotFoundException;


    // Tests for user's CRUD operations //

    void testUserCreate();

    void testUserUpdate();

    void testUserGetById();

    void testUserList();

    void testUserDelete() throws UserNotFoundException;
}
