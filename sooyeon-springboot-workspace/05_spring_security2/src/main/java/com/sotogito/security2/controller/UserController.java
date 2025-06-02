package com.sotogito.security2.controller;

import com.sotogito.security2.dto.UserDto;
import com.sotogito.security2.dto.auth.LoginUser;
import com.sotogito.security2.service.UserDetailsServiceImpl;
import com.sotogito.security2.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserServiceImpl userService;
    private final UserDetailsServiceImpl userDetailsService;

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
    public void userMainPage(@AuthenticationPrincipal LoginUser loginUser) {
        System.out.println("아이디 : " + loginUser.getUsername());
        System.out.println("비번 : " + loginUser.getPassword()); //암호화때문에  null
        System.out.println("권한목록 : " + loginUser.getAuthorities());
        System.out.println("회원명 : " + loginUser.getDisplayUserName());

    }

    @PostMapping("/signup")
    public String signupPage(UserDto user,
                             RedirectAttributes redirectAttributes) {
        Map<String, String> result = userService.registUser(user);
        redirectAttributes.addFlashAttribute("message", result.get("message"));

        return "redirect:"+ result.get("path");
    }

}
