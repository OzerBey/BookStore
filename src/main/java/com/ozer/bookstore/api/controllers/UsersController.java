package com.ozer.bookstore.api.controllers;

import com.ozer.bookstore.business.abstracts.UserService;
import com.ozer.bookstore.core.utilities.exceptions.UserNotFoundException;
import com.ozer.bookstore.core.utilities.results.*;
import com.ozer.bookstore.entities.concretes.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("add")
    public Result add(@RequestBody User user) {
        return this.userService.add(user);
    }

    @PutMapping("update")
    public Result update(@RequestBody User user) throws UserNotFoundException {
        return this.userService.update(user);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestParam("id") int id) throws UserNotFoundException {
        return this.userService.deleteById(id);
    }

    @GetMapping("getById")
    public DataResult<Optional<User>> getById(@RequestParam(name = "id") int id) {
        return this.userService.getById(id);
    }

    @GetMapping("getAll")
    public DataResult<List<User>> getAll() {
        return this.userService.getAll();
    }
}
