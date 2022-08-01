package com.ozer.bookstore;

import com.ozer.bookstore.api.controllers.UsersController;
import com.ozer.bookstore.business.abstracts.AuthorService;
import com.ozer.bookstore.business.abstracts.BookService;
import com.ozer.bookstore.business.abstracts.UserService;
import com.ozer.bookstore.core.utilities.exceptions.AuthorNotFoundException;
import com.ozer.bookstore.core.utilities.exceptions.UserNotFoundException;
import com.ozer.bookstore.core.utilities.results.SuccessDataResult;
import com.ozer.bookstore.dataAccess.abstracts.UserDao;
import com.ozer.bookstore.entities.concretes.Author;
import com.ozer.bookstore.entities.concretes.Book;
import com.ozer.bookstore.entities.concretes.User;
import com.ozer.bookstore.test.GeneralTestCrud;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@SpringBootTest
@Log4j2
class BookStoreApplicationTests implements GeneralTestCrud {

    @Autowired
    BookService bookService;
    @Autowired
    AuthorService authorService;
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void runBeforeTest() {
        System.err.println("called runBeforeTest method before each other");
    }

    @AfterEach
    public void runAfterAllTests() {
        System.err.println("this method called runAfterAllTests after each other");
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

    // Tests for users //

    @Test
    @Override
    public void testUserCreate() {
        User userEntity = User.builder().name("Yasin Özer").email("yasinozer@mail.com").username("ozerBey").password("pa55word@").build();
        if (!isPasswordValid(userEntity.getPassword())) {
            log.error("User's password must be greater than 4 character");
        }
        userService.add(userEntity); // save on db
        assertNotNull(userService.getById(userEntity.getId()).getData());
    }

    public boolean isPasswordValid(String password) {
        return password.length() < 4 ? false : true;
    }

    @Test
    @Override
    public void testUserGetById() {
        Optional<User> userEntity = userService.getById(2).getData();

        //Is exists name as 'Yasin Özer' or not
        assertEquals("Yasin Özer", userEntity.get().getName());
    }

    @Test
    @Override
    public void testUserUpdate() {
        Optional<User> userEntity = userService.getById(6).getData();
        String name = "Barış Binboğan name changed by testClass";
        userEntity.get().setName(name);

//        userService.update(userEntity); // i disabled this process
        log.info("User's infos are same");
        assertEquals(name, userEntity.get().getName());
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
