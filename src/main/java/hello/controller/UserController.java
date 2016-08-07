package hello.controller;

import hello.controller.viewmodel.UserCreateViewModel;
import hello.persistance.User;
import hello.repository.UserDao;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserDao userDao;
    private UserService userService;

    @Autowired
    public UserController(UserService userService, UserDao userDao) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showForm() {
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String create(Model model, UserCreateViewModel userCreateViewModel) {
        boolean userCreated = userService.createUser(userCreateViewModel);
        model.addAttribute("isUserCreated", userCreated);
            model.addAttribute("createUserResponse", userCreated ? "User succesfully created!" : "User already registered.");
        return "/user/register";
    }





}