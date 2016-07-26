package hello.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthorizedController {

    @RequestMapping("/naber")
    @ResponseBody
    public String naber() {
        return "naber";
    }
}
