package hello.controller;

import  hello.controller.viewmodel.UserCreateViewModel;
import hello.persistance.Status;
import hello.persistance.Todo;
import hello.persistance.User;
import hello.repository.TodoDao;
import hello.repository.UserDao;
import hello.service.TodoService;
import hello.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/")
public class ServiceController {
    UserCreateViewModel userCreateViewModel=new UserCreateViewModel("","","","");
    TodoService todoService;
    TodoDao todoDao;

    @Autowired
    UserService userService;

        static final Logger logger = Logger.getLogger(ServiceController.class);

        @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
        public
        @ResponseBody
        User getUserById(@PathVariable("id") long id) {
            User user = null;

            try {
                user = userService.getUserById(id);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return user;
        }


    @RequestMapping(value="/list", method = RequestMethod.GET)
    public @ResponseBody
    List<User> findAll() {
        List<User> userList = null;
        try {
            userList = userService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
    @RequestMapping(value = "user/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Status create(@RequestBody UserCreateViewModel userCreateViewModel){
        try{
            userService.createUser(userCreateViewModel);
            return new Status(1,"user Added");
        } catch (Exception e) {
            return new Status(0, e.toString());
        }
    }


}

