package com.ozer.bookstore.business.concretes;

import com.ozer.bookstore.business.abstracts.SequenceGeneratorService;
import com.ozer.bookstore.business.abstracts.UserService;
import com.ozer.bookstore.core.utilities.exceptions.AuthorNotFoundException;
import com.ozer.bookstore.core.utilities.exceptions.UserNotFoundException;
import com.ozer.bookstore.core.utilities.results.*;
import com.ozer.bookstore.dataAccess.abstracts.AuthorDao;
import com.ozer.bookstore.dataAccess.abstracts.UserDao;
import com.ozer.bookstore.entities.concretes.Author;
import com.ozer.bookstore.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManager implements UserService {

    private UserDao userDao;
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public UserManager(UserDao userDao, SequenceGeneratorService sequenceGeneratorService) {
        this.userDao = userDao;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public Result add(User user) {
        user.setId(sequenceGeneratorService.getSequenceNumber(user.SEQUENCE_NAME));
        this.userDao.save(user);
        return new SuccessResult("User added successfully");
    }

    @Override
    public Result deleteById(int id) throws UserNotFoundException {
        if (this.userDao.existsById(id)) {
            isExistsUser(id);
            this.userDao.deleteById(id);
            return new SuccessResult("Author deleted from db successfully");
        } else new AuthorNotFoundException();
        return new ErrorResult();
    }

    @Override
    public Result update(User user) throws UserNotFoundException {
        if (this.userDao.existsById(user.getId())) {
            isExistsUser(user.getId());
            this.userDao.save(user);
            return new SuccessResult(user.getName() + " updated successfully");
        } else new AuthorNotFoundException();
        return new ErrorResult();
    }

    @Override
    public DataResult<Optional<User>> getById(int userId) {
        if (this.userDao.existsById(userId)) {
            return new SuccessDataResult<>(this.userDao.findById(userId), "user listed");
        } else new UserNotFoundException();
        return new ErrorDataResult<Optional<User>>();
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<>(this.userDao.findAll(), "All users listed");

    }

    //check user
    public void isExistsUser(int userId) throws UserNotFoundException {
        if (!this.userDao.existsById(userId)) {
            throw new UserNotFoundException();
        }
    }
}
