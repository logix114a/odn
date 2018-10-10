package com.noblens.odn.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {



    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "user/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user/user";
    }

    @GetMapping("/about")
    public String about() {
        return "user/about";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
    @GetMapping("/user/login")
    public String loginuser() {
        return "user/login";
    }
    @GetMapping("/403")
    public String error403() {
        return "user/error/403";
    }

}
