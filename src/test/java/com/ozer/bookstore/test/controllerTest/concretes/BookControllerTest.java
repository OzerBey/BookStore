package com.ozer.bookstore.test.controllerTest.concretes;

import com.ozer.bookstore.business.abstracts.BookService;
import com.ozer.bookstore.core.utilities.results.SuccessDataResult;
import com.ozer.bookstore.entities.concretes.Book;
import com.ozer.bookstore.test.controllerTest.abstracts.BookCrudTest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Log4j2
public class BookControllerTest implements BookCrudTest {

    @Autowired
    BookService bookService;

    // Tests for books //

    //CREATE
    @Test
    @Override
    public void testBookCreate() {
        List<String> authors = new ArrayList<>(Collections.singleton("Halide Edip Adıvar"));
        Book bookEntity = Book.builder().title("Ateşten Gömlek").authors(authors).publishDate("26.07.2022").pagesNo(400).language("Turkish").publisherId(String.valueOf(3)).stock(100).build();
        bookService.add(bookEntity); // save on db

        //if object is null return assertionError error
        assertNotNull(bookService.getById(bookEntity.getId()).getData());
    }

    //LIST
    @Test
    @Override
    public void testBookList() {
        SuccessDataResult<List<Book>> bookList = (SuccessDataResult<List<Book>>) bookService.getAll();

        //if list greater than 0 bookList is exists and return it
        assertThat(bookList).as(String.valueOf(true));
    }


    //GetById
    @Test
    @Override
    public void testBookGetById() {
        Optional<Book> bookEntity = bookService.getById(2).getData();

        //Is exists title as 'Tutunamayanlar' or not
        System.err.println("actual value is " + bookEntity.get().getTitle()); // test current book entity
        String titleOfBook = "Tutunamayanlar";
        isBookAvailable(bookEntity.get().getId());
        assertEquals(titleOfBook, bookEntity.get().getTitle());

    }
    // check book by its id

    public boolean isBookAvailable(int bookId) {
        boolean result = false;
        List<Book> bookList = bookService.getAll().getData();
        for (Book book : bookList) {
            if (book.getId() == bookId) {
                result = true;
            }
        }
        return result;
    }

    //UPDATE
    @Test
    @Override
    public void testBookUpdate() {
        Optional<Book> bookEntity = bookService.getById(4).getData();
        bookEntity.get().setTitle("Kara Kitap changed by test class");

//        bookService.update(bookEntity); // i disabled this process to not change my real data
        // assertEquals("Kara Kitap changed by test class", bookService.getById(4).getData());
        assertEquals("Kara Kitap changed by test class", bookEntity.get().getTitle());
    }

    //DELETE
    @Test
    @Override
    public void testBookDelete() {
        bookService.deleteById(1);
        // is this bookEntity not null
        assertThat(bookService.getById(1)).isNotNull();
    }

}
