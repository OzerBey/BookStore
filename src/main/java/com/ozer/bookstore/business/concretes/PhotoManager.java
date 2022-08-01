package com.ozer.bookstore.business.concretes;

import com.ozer.bookstore.business.abstracts.PhotoService;
import com.ozer.bookstore.business.abstracts.SequenceGeneratorService;
import com.ozer.bookstore.core.utilities.exceptions.PhotoNotFoundException;
import com.ozer.bookstore.core.utilities.exceptions.UserNotFoundException;
import com.ozer.bookstore.core.utilities.results.*;
import com.ozer.bookstore.dataAccess.abstracts.PhotoDao;
import com.ozer.bookstore.entities.concretes.Photo;
import com.ozer.bookstore.entities.concretes.User;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PhotoManager implements PhotoService {

    private PhotoDao photoDao;
    private SequenceGeneratorService sequenceGeneratorService;


    @Autowired
    public PhotoManager(PhotoDao photoDao, SequenceGeneratorService sequenceGeneratorService) {
        this.photoDao = photoDao;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public Result add(Photo photo) {
        photo.setId(sequenceGeneratorService.getSequenceNumber(photo.SEQUENCE_NAME));
        this.photoDao.save(photo);
        return new SuccessResult("Photo added successfully");
    }

    @Override
    public Result deleteById(int id) throws PhotoNotFoundException {
        if (this.photoDao.existsById(id)) {
            isExistsPhoto(id);
            this.photoDao.deleteById(id);
            return new SuccessResult("Photo deleted from db successfully");
        } else new UserNotFoundException();
        return new ErrorResult();
    }

    @Override
    public Result update(Photo photo) throws PhotoNotFoundException {
        if (this.photoDao.existsById(photo.getId())) {
            isExistsPhoto(photo.getId());
            this.photoDao.save(photo);
            return new SuccessResult(photo.getTitle() + " updated successfully");
        } else new PhotoNotFoundException();
        return new ErrorResult();
    }

    @Override
    public DataResult<Optional<Photo>> getById(int photoId) {
        if (this.photoDao.existsById(photoId)) {
            return new SuccessDataResult<Optional<Photo>>(this.photoDao.findById(photoId), "photo listed");
        } else new PhotoNotFoundException();
        return new ErrorDataResult<Optional<Photo>>();
    }

    @Override
    public DataResult<List<Photo>> getAll() {
        return new SuccessDataResult<>(this.photoDao.findAll(), "All photos listed");

    }

    //check photo
    public void isExistsPhoto(int photoId) throws PhotoNotFoundException {
        if (!this.photoDao.existsById(photoId)) {
            log.error("PhotoNotFound!!");
            throw new PhotoNotFoundException();
        }
    }
}
