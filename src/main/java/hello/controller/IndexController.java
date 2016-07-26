package hello.controller;

import hello.persistance.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/")
    public String index(Model model, @AuthenticationPrincipal User user) {
        if (user != null) {
            return "redirect:/todo/";
        }
        return "index";
    }

}