package com.sotogito.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public void loginPage() {
    }

    @GetMapping("/signup")
    public void signupPage() {
    }

    @GetMapping("/admin/main")
    public void adminMainPage() {
    }

    @GetMapping("/user/main")
    public void userMainPage() {
    }

}
