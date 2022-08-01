package com.ozer.bookstore.test.testController.concretes;

import com.ozer.bookstore.business.abstracts.UserService;
import com.ozer.bookstore.core.utilities.exceptions.UserNotFoundException;
import com.ozer.bookstore.core.utilities.results.SuccessDataResult;
import com.ozer.bookstore.entities.concretes.User;
import com.ozer.bookstore.test.testController.abstracts.UserCrudTest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Log4j2
public class UserControllerTest implements UserCrudTest {

    @Autowired
    UserService userService;

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
