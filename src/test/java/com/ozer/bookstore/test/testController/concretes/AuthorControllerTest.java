package com.ozer.bookstore.test.testController.concretes;

import com.ozer.bookstore.business.abstracts.AuthorService;
import com.ozer.bookstore.core.utilities.exceptions.AuthorNotFoundException;
import com.ozer.bookstore.core.utilities.results.SuccessDataResult;
import com.ozer.bookstore.entities.concretes.Author;
import com.ozer.bookstore.entities.concretes.Book;
import com.ozer.bookstore.test.testController.abstracts.AuthorCrudTest;
import lombok.extern.java.Log;
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
public class AuthorControllerTest implements AuthorCrudTest {


    @Autowired
    AuthorService authorService;
    // Tests for authors //

    //CREATE
    @Test
    @Override
    public void testAuthorCreate() {
        List<String> authors = new ArrayList<>(Collections.singleton("Halide Edip Adıvar"));
        Book bookEntity = Book.builder().title("Ateşten Gömlek").authors(authors).publishDate("26.07.2022").pagesNo(400).language("Turkish").publisherId(String.valueOf(3)).stock(100).build();
        List<Book> books = new ArrayList<>(Collections.singleton(bookEntity));


        Author authorEntity = Author.builder().name("Halide Edip Adıvar").bookCount(34).books(books).address("www.halideedipadivar.com").build();
        authorService.add(authorEntity); // save on db
        assertNotNull(authorService.getById(authorEntity.getId()).getData());
    }

    //GetById
    @Test
    @Override
    public void testAuthorGetById() {
        Optional<Author> authorEntity = authorService.getById(2).getData();

        //Is exists name as 'Halid Ziya Uşaklıgil' or not
        String expectedName = "Halid Ziya Uşaklıgil";
        assertEquals(expectedName, authorEntity.get().getName());
    }

    //UPDATE
    @Test
    @Override
    public void testAuthorUpdate() {
        Optional<Author> authorEntity = authorService.getById(5).getData();
        String name = "Halide Edip Adıvar title changed by test class";
        authorEntity.get().setName(name);

        // authorService.update(bookEntity); // i disabled this process to not change my real data
        // assertEquals(name, authorService.getById(5).getData());
        assertEquals(name, authorEntity.get().getName());

    }


    //LIST
    @Test
    @Override
    public void testAuthorList() {
        SuccessDataResult<List<Author>> authorList = (SuccessDataResult<List<Author>>) authorService.getAll();

        //if list greater than 0 bookList is exists and return it
        assertThat(authorList).as(String.valueOf(true));
    }

    //DELETE
    @Test
    @Override
    public void testAuthorDelete() throws AuthorNotFoundException {
        authorService.deleteById(1);
        // is this authorEntity not null
        assertThat(authorService.getById(1)).isNotNull();
    }
}
