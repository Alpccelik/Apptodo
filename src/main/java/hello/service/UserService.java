package hello.service;

import hello.controller.viewmodel.UserCreateViewModel;
import hello.enums.UserRole;
import hello.persistance.User;
import hello.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean createUser(UserCreateViewModel viewModel) {
        if (userDao.findOneByUsernameOrEmail(viewModel.getUsername(), viewModel.getEmail()) == null) {
            if (userDao.save(new User(viewModel.getUsername(), viewModel.getPassword(), viewModel.getEmail(), viewModel.getName(), Collections.singleton(UserRole.USER))) != null) {
                return true;
            }
        }
        return false;
    }

    public User findEnabledUser(String username) {
        return userDao.findOneByUsernameAndEnabledTrue(username);
    }

    public User getUserById(long id){
        return userDao.getUserById(id);
    }


    public List<User> findAll() {
        return  userDao.findAll();
    }
}
