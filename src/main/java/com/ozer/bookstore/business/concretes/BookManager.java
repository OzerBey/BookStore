package com.ozer.bookstore.business.concretes;

import com.ozer.bookstore.business.abstracts.BookService;
import com.ozer.bookstore.business.abstracts.SequenceGeneratorService;
import com.ozer.bookstore.core.utilities.results.DataResult;
import com.ozer.bookstore.core.utilities.results.Result;
import com.ozer.bookstore.core.utilities.results.SuccessDataResult;
import com.ozer.bookstore.core.utilities.results.SuccessResult;
import com.ozer.bookstore.dataAccess.abstracts.BookDao;
import com.ozer.bookstore.entities.concretes.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        this.bookDao.deleteById(id);
        return new SuccessResult("Book deleted successfully");
    }

    @Override
    public Result update(Book book) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(book.getId()));
        Update update = new Update();
//        update.set("title", book.getTitle());
//        update.set("authors", book.getAuthors());
//        update.set("publish_date", book.getPublishDate());
//        update.set("pagesNo", book.getPagesNo());
//        update.set("language", book.getLanguage());
//        update.set("publisher_id", book.getPublisherId());
//        update.set("available", book.getStock());
        bookDao.save(book);
        return new SuccessResult("Book updated successfully");
    }

    @Override
    public SuccessDataResult<Optional<Book>> getById(int bookId) {
        return new SuccessDataResult<Optional<Book>>(this.bookDao.findById(bookId), "Book got successfully");
    }

    @Override
    public DataResult<List<Book>> getAll() {

        return new SuccessDataResult<List<Book>>(this.bookDao.findAll(), "Books listed successfully");
    }
}
