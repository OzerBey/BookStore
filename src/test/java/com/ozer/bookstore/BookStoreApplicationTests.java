package com.ozer.bookstore;

import com.ozer.bookstore.business.abstracts.AuthorService;
import com.ozer.bookstore.business.abstracts.BookService;
import com.ozer.bookstore.business.abstracts.UserService;
import com.ozer.bookstore.core.utilities.exceptions.AuthorNotFoundException;
import com.ozer.bookstore.core.utilities.exceptions.UserNotFoundException;
import com.ozer.bookstore.core.utilities.results.DataResult;
import com.ozer.bookstore.core.utilities.results.SuccessDataResult;
import com.ozer.bookstore.entities.concretes.Author;
import com.ozer.bookstore.entities.concretes.Book;
import com.ozer.bookstore.entities.concretes.User;
import com.ozer.bookstore.test.TestCrud;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@SpringBootTest
class BookStoreApplicationTests implements TestCrud {

    @Autowired
    BookService bookService;
    @Autowired
    AuthorService authorService;

    @Autowired
    UserService userService;


    @Test
    void contextLoads() {
    }

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
    public DataResult<Optional<Book>> testBookGetById() {
        Optional<Book> bookEntity = bookService.getById(1).getData();

        //Is exists title as 'Ateşten Gömlek' or not
        System.err.println("actual value is " + bookEntity.get().getTitle());
        assertEquals("Ateşten Gömlek", bookEntity.get().getTitle());
        return new SuccessDataResult<>();
    }

    //UPDATE
    @Test
    @Override
    public void testBookUpdate() {
        Optional<Book> bookEntity = bookService.getById(1).getData();
        bookEntity.get().setTitle("Ateşten Gömlek title from test");

        // bookService.update(bookEntity);
        assertEquals("Ateşten Gömlek title from test", bookService.getById(1).getData());
    }

    //DELETE
    @Test
    @Override
    public void testBookDelete() {
        bookService.deleteById(1);
        // is this bookEntity not null
        assertThat(bookService.getById(1)).isNotNull();
    }

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
    }

    //GetById
    @Test
    @Override
    public DataResult<Optional<Book>> testAuthorGetById() {
        Optional<Author> authorEntity = authorService.getById(1).getData();

        //Is exists name as 'Oğuz Atay' or not
        assertEquals("Oğuz Atay", authorEntity.get().getName());
        return new SuccessDataResult<>();
    }

    //UPDATE
    @Test
    @Override
    public void testAuthorUpdate() {
        Optional<Author> authorEntity = authorService.getById(1).getData();
        authorEntity.get().setName("Halide Edip Adıvar title from test");

        // bookService.update(bookEntity);
        assertNull(bookService.getById(1).getData());
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

    // Tests for users //

    @Test
    @Override
    public void testUserCreate() {
        User userEntity = User.builder().name("Yasin Özer").email("yasinozer@mail.com").username("ozerBey").password("pa55word@").build();
        userService.add(userEntity); // save on db
    }

    @Test
    @Override
    public DataResult<Optional<Book>> testUserGetById() {
        Optional<User> userEntity = userService.getById(1).getData();

        //Is exists name as 'Yasin Ozer' or not
        assertEquals("Yasin Ozer", userEntity.get().getName());
        return new SuccessDataResult<>();

    }

    @Test
    @Override
    public void testUserUpdate() {
        Optional<User> userEntity = userService.getById(1).getData();
        userEntity.get().setName("Yasin Ozer from test");

//        userService.update(userEntity);
        assertNull(bookService.getById(1).getData());
    }

    @Test
    @Override
    public void testUserList() {
        SuccessDataResult<List<User>> userList = (SuccessDataResult<List<User>>) userService.getAll();

        //if list greater than 0 userList is exists and return it
        assertThat(userList).as(String.valueOf(true));
    }

    @Test
    @Override
    public void testUserDelete() throws UserNotFoundException {
        userService.deleteById(1);
        // is this userEntity not null
        assertThat(userService.getById(1)).isNotNull();
    }
}
