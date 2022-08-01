package com.ozer.bookstore.business.abstracts;


import com.ozer.bookstore.core.utilities.exceptions.PhotoNotFoundException;
import com.ozer.bookstore.core.utilities.results.DataResult;
import com.ozer.bookstore.core.utilities.results.Result;
import com.ozer.bookstore.entities.concretes.Photo;

import java.util.List;
import java.util.Optional;

public interface PhotoService {
    Result add(Photo photo);

    Result deleteById(int id) throws PhotoNotFoundException;

    Result update(Photo photo) throws PhotoNotFoundException;

    DataResult<Optional<Photo>> getById(int photoId);

    DataResult<List<Photo>> getAll();
}
