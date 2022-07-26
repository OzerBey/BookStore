package com.ozer.bookstore.dataAccess.abstracts;

import com.ozer.bookstore.entities.concretes.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User, Integer> {
    User findByEmail(String email);
}
