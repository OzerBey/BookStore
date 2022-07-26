package com.ozer.bookstore.dataAccess.abstracts;

import com.ozer.bookstore.entities.concretes.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorDao extends MongoRepository<Author, Integer> {
}
