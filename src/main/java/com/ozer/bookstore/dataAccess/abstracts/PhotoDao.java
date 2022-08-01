package com.ozer.bookstore.dataAccess.abstracts;

import com.ozer.bookstore.entities.concretes.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoDao extends MongoRepository<Photo, Integer> {
}
