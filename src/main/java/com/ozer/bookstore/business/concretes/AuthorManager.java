package com.ozer.bookstore.business.concretes;

import com.ozer.bookstore.business.abstracts.AuthorService;
import com.ozer.bookstore.business.abstracts.SequenceGeneratorService;
import com.ozer.bookstore.core.utilities.exceptions.AuthorNotFoundException;
import com.ozer.bookstore.core.utilities.results.*;
import com.ozer.bookstore.dataAccess.abstracts.AuthorDao;
import com.ozer.bookstore.entities.concretes.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorManager implements AuthorService {

    private AuthorDao authorDao;
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public AuthorManager(AuthorDao authorDao, SequenceGeneratorService sequenceGeneratorService) {
        this.authorDao = authorDao;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public Result add(Author author) {
        author.setId(sequenceGeneratorService.getSequenceNumber(author.SEQUENCE_NAME));
        this.authorDao.save(author);
        return new SuccessResult("Author added successfully");
    }

    @Override
    public Result deleteById(int id) throws AuthorNotFoundException {
        if (this.authorDao.existsById(id)) {
            isExistsAuthor(id);
            this.authorDao.deleteById(id);
            return new SuccessResult("Author deleted from db successfully");
        } else new AuthorNotFoundException();
        return new ErrorResult();
    }

    @Override
    public Result update(Author author) throws AuthorNotFoundException {
        if (this.authorDao.existsById(author.getId())) {
            isExistsAuthor(author.getId());
            this.authorDao.save(author);
            return new SuccessResult(author.getName() + " updated successfully");
        } else new AuthorNotFoundException();
        return new ErrorResult();
    }

    @Override
    public DataResult<Optional<Author>> getById(int authorId) {
        if (this.authorDao.existsById(authorId)) {
            return new SuccessDataResult<>(this.authorDao.findById(authorId), "author listed");
        } else new AuthorNotFoundException();
        return new ErrorDataResult<Optional<Author>>();
    }

    @Override
    public DataResult<List<Author>> getAll() {
        return new SuccessDataResult<>(this.authorDao.findAll(), "All author listed");
    }

    //check author
    public void isExistsAuthor(int authorId) throws AuthorNotFoundException {
        if (!this.authorDao.existsById(authorId)) {
            System.err.println("not found author");
            throw new AuthorNotFoundException();
        }
    }
}
