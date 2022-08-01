package com.ozer.bookstore.test.controllerTest.abstracts;

import com.ozer.bookstore.core.utilities.exceptions.UserNotFoundException;

public interface UserCrudTest {
    // Tests for user's CRUD operations //

    void testUserCreate();

    void testUserUpdate();

    void testUserGetById();

    void testUserList();

    void testUserDelete() throws UserNotFoundException;
}
