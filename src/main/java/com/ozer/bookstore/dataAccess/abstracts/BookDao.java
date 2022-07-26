package com.ozer.bookstore.dataAccess.abstracts;

import com.ozer.bookstore.entities.concretes.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookDao extends MongoRepository<Book, Integer> {

}
