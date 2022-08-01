package com.ozer.bookstore.business.concretes;

import com.ozer.bookstore.business.abstracts.BookService;
import com.ozer.bookstore.business.abstracts.SequenceGeneratorService;
import com.ozer.bookstore.core.utilities.exceptions.AuthorNotFoundException;
import com.ozer.bookstore.core.utilities.exceptions.BookNotFoundException;
import com.ozer.bookstore.core.utilities.results.*;
import com.ozer.bookstore.dataAccess.abstracts.BookDao;
import com.ozer.bookstore.entities.concretes.Book;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class BookManager implements BookService {

    private BookDao bookDao;
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public BookManager(BookDao bookDao, SequenceGeneratorService sequenceGeneratorService) {
        this.bookDao = bookDao;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public Result add(Book book) {
        //generate sequence
        book.setId(sequenceGeneratorService.getSequenceNumber(book.SEQUENCE_NAME));
        this.bookDao.save(book);
        return new SuccessResult(book.getTitle() + " added successfully");
    }

    @Override
    public Result deleteById(int id) {
        if (this.bookDao.existsById(id)) {
            this.bookDao.deleteById(id);
            return new SuccessResult("Book deleted successfully");
        } else new BookNotFoundException();
        return new ErrorResult();
    }

    @Override
    public Result update(Book book) {
        if (this.bookDao.existsById(book.getId())) {
            this.bookDao.save(book);
            return new SuccessResult("Book updated successfully");
        } else new BookNotFoundException();
        return new ErrorResult();
    }

    @Override
    public DataResult<Optional<Book>> getById(int bookId) {
        if (this.bookDao.existsById(bookId)) {
            return new SuccessDataResult<Optional<Book>>(this.bookDao.findById(bookId), "Book got successfully");
        } else new BookNotFoundException();
        return new ErrorDataResult<>();
    }

    @Override
    public DataResult<List<Book>> getAll() {
        return new SuccessDataResult<List<Book>>(this.bookDao.findAll(), "Books listed successfully");
    }

    //check book that exists
    public void isExistsBook(int bookId) throws BookNotFoundException {
        if (!this.bookDao.existsById(bookId)) {
            log.error("BookNotFound!!");
            throw new BookNotFoundException();
        }
    }
}
