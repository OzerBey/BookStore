package com.ozer.bookstore.business.abstracts;

import com.ozer.bookstore.core.utilities.exceptions.AuthorNotFoundException;
import com.ozer.bookstore.core.utilities.exceptions.UserNotFoundException;
import com.ozer.bookstore.core.utilities.results.DataResult;
import com.ozer.bookstore.core.utilities.results.Result;
import com.ozer.bookstore.entities.concretes.Author;
import com.ozer.bookstore.entities.concretes.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Result add(User user);

    Result deleteById(int id) throws UserNotFoundException;

    Result update(User user) throws UserNotFoundException;

    DataResult<Optional<User>> getById(int userId);

    DataResult<List<User>> getAll();
}
