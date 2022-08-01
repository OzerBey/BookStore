package com.ozer.bookstore.test.controllerTest.abstracts;

import com.ozer.bookstore.core.utilities.exceptions.AuthorNotFoundException;

public interface AuthorCrudTest {
    // Tests for author's CRUD operations //

    void testAuthorCreate();

    void testAuthorUpdate();

    void testAuthorGetById();

    void testAuthorList();

    void testAuthorDelete() throws AuthorNotFoundException;

}
